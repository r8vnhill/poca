package cl.ravenhill.poca

import io.kotest.core.spec.style.WordSpec
import io.kotest.datatest.withData
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.haveSameHashCodeAs

private const val CHARIZARD_NAME = "Charizard"
private const val CHARIZARD_TYPE = "Fire"
private const val CHARIZARD_HP = 120
private const val MEW_NAME = "Mew"
private const val MEW_TYPE = "Psychic"
private const val MEW_HP = 60
private const val PIKACHU_NAME = "Pikachu"
private const val PIKACHU_TYPE = "Electric"
private const val PIKACHU_HP = 60

data class CardData(
    val name: String,
    val type: String,
    val maxHp: Int
)

/**
 * Tests for [Card].
 */
class CardSpec : WordSpec({

    "Two cards with the same parameters should be equal" When {
        withData(
            CardData(CHARIZARD_NAME, CHARIZARD_TYPE, CHARIZARD_HP),
            CardData(MEW_NAME, MEW_TYPE, MEW_HP),
            CardData(PIKACHU_NAME, PIKACHU_TYPE, PIKACHU_HP)
        ) { (name, type, hp) ->
            Card(name, type, hp) shouldBe Card(name, type, hp)
        }
    }

    "Two cards with the same parameters should have the same hash code" When {
        withData(
            CardData(CHARIZARD_NAME, CHARIZARD_TYPE, CHARIZARD_HP),
            CardData(MEW_NAME, MEW_TYPE, MEW_HP),
            CardData(PIKACHU_NAME, PIKACHU_TYPE, PIKACHU_HP)
        ) { (name, type, hp) ->
            Card(name, type, hp) should haveSameHashCodeAs(Card(name, type, hp))
        }
    }

    "Two cards with different parameters should not be equal" When {
        withData(
            CardData(CHARIZARD_NAME, CHARIZARD_TYPE, CHARIZARD_HP),
            CardData(MEW_NAME, CHARIZARD_TYPE, CHARIZARD_HP),
            CardData(CHARIZARD_NAME, PIKACHU_TYPE, CHARIZARD_HP)
        ) { (name, type, hp) ->
            Card(name, type, hp) shouldNotBe Card(name, type, hp + 1)
            Card(name, type, hp) shouldNotBe Card(name, "Other type", hp)
            Card(name, type, hp) shouldNotBe Card("Other name", type, hp)
        }
    }

    "Two cards with different parameters should not have the same hash codes" When {
        withData(
            CardData(CHARIZARD_NAME, CHARIZARD_TYPE, CHARIZARD_HP),
            CardData(MEW_NAME, CHARIZARD_TYPE, CHARIZARD_HP),
            CardData(CHARIZARD_NAME, PIKACHU_TYPE, CHARIZARD_HP)
        ) { (name, type, hp) ->
            Card(name, type, hp) shouldNot haveSameHashCodeAs(Card(name, type, hp + 1))
            Card(name, type, hp) shouldNot haveSameHashCodeAs(Card(name, "Other type", hp))
            Card(name, type, hp) shouldNot haveSameHashCodeAs(Card("Other name", type, hp))
        }
    }
})
