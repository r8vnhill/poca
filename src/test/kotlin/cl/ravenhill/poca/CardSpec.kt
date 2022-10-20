package cl.ravenhill.poca

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.haveSameHashCodeAs
import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll

data class CardData(
    val name: String,
    val type: String,
    val maxHp: Int,
    val attack: Int
)

fun cardArb(minHp: Int = 1, maxHp: Int = 100, minAtk: Int = 1, maxAtk: Int = 100) =
    arbitrary {
        val name = Arb.string().bind()
        val type = Arb.string().bind()
        val hp = Arb.int(minHp..maxHp).bind()
        val attack = Arb.int(minAtk..maxAtk).bind()
        CardData(name, type, hp, attack)
    }

private fun cardPair(card1: CardData, card2: CardData) =
    Card(card1.name, card1.type, card1.maxHp, card1.attack) to
            Card(card2.name, card2.type, card2.maxHp, card2.attack)

class CardSpec : WordSpec({
    "Two cards with the same parameters" should {
        "be equal" {
            checkAll<String, String, Int, Int> { name, type, maxHp, attack ->
                val card1 = Card(name, type, maxHp, attack)
                val card2 = Card(name, type, maxHp, attack)
                card1 shouldBe card2
            }
        }

        "have the same hash code" {
            checkAll<String, String, Int, Int> { name, type, maxHp, attack ->
                val card1 = Card(name, type, maxHp, attack)
                val card2 = Card(name, type, maxHp, attack)
                card1 should haveSameHashCodeAs(card2)
            }
        }
    }

    "An attack" should {
        "deal damage to the target" {
            checkAll(
                cardArb(minHp = 50),
                cardArb(maxAtk = 50)
            ) { card1, card2 ->
                val (c1, c2) = cardPair(card1, card2)
                c2.attack(c1)
                c1.hp shouldBe c1.maxHp - c2.attack

            }
        }
        "be able to ko the target" {
            checkAll(
                cardArb(maxHp = 50),
                cardArb(minAtk = 50)
            ) { card1, card2 ->
                val (c1, c2) = cardPair(card1, card2)
                c2.attack(c1)
                c1.isKo() shouldBe true

            }
        }
    }

})