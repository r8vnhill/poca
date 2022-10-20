package cl.ravenhill.poca

import java.util.*
import kotlin.math.max
import kotlin.math.min

class Card(
  val name: String,
  val type: String,
  val maxHp: Int,
  val attack: Int
) {
  var hp: Int = maxHp
    private set(newHp) {
      field = max(0, min(newHp, maxHp))
    }

  override fun equals(other: Any?) = when {
    this === other -> true
    other !is Card -> false
    else -> Card::class == other::class
        && name == other.name
        && type == other.type
        && maxHp == other.maxHp
  }

  override fun hashCode() =
    Objects.hash(Card::class, name, type, maxHp)

  override fun toString() =
    "Card(name: '$name', type: '$type', maxHp: $maxHp)"

  fun attack(opponent: Card) {
    if (!this.isKo()) {
      opponent.hp -= attack
    }
  }

  fun isKo() = hp == 0
}
