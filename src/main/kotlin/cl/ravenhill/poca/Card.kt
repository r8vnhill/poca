package cl.ravenhill.poca

import java.util.*

class Card(
  val name: String,
  val type: String,
  val maxHp: Int
) {
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
}
