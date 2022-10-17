package cl.ravenhill.poca

import io.kotest.core.spec.style.WordSpec
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

class CardSpec : WordSpec({
  lateinit var charizard: Card
  lateinit var mew: Card

  beforeEach {
    charizard = Card(CHARIZARD_NAME, CHARIZARD_TYPE, CHARIZARD_HP)
    mew = Card(MEW_NAME, MEW_TYPE, MEW_HP)
  }

  "Two cards with the same parameters" should {
    "Be equal" {
      val charizard2 =
        Card(CHARIZARD_NAME, CHARIZARD_TYPE, CHARIZARD_HP)
      charizard shouldBe charizard2
      val mew2 = Card(MEW_NAME, MEW_TYPE, MEW_HP)
      mew shouldBe mew2
    }

    "Have the same hash code" {
      val charizard2 =
        Card(CHARIZARD_NAME, CHARIZARD_TYPE, CHARIZARD_HP)
      charizard should haveSameHashCodeAs(
        charizard2
      )
      val mew2 = Card(MEW_NAME, MEW_TYPE, MEW_HP)
      mew should haveSameHashCodeAs(mew2)
    }
  }

  "Two Pokémon with different parameters" should {
    "Not be equal" {
      val charizard2 =
        Card(CHARIZARD_NAME, CHARIZARD_TYPE, 100)
      charizard shouldNotBe charizard2
      val mew2 = Card(MEW_NAME, MEW_TYPE, 100)
      mew shouldNotBe mew2
    }

    "Not have the same hash code" {
      val charizard2 =
        Card(CHARIZARD_NAME, CHARIZARD_TYPE, 100)
      charizard shouldNot haveSameHashCodeAs(
        charizard2
      )
      val mew2 = Card(MEW_NAME, MEW_TYPE, 100)
      mew shouldNot haveSameHashCodeAs(mew2)
    }
  }
})