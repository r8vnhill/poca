package cl.ravenhill.poca

import java.util.Objects

/**
 * A Pokémon card.
 *
 * @param name  The name of the Pokémon.
 * @param type  The type of the Pokémon.
 * @param maxHp The maximum HP of the Pokémon.
 */
class Card(
    val name: String,
    val type: String,
    val maxHp: Int
) {
    override fun equals(other: Any?) = when {
        this === other -> true
        other !is Card -> false
        Card::class != other::class -> false
        name != other.name -> false
        type != other.type -> false
        maxHp != other.maxHp -> false
        else -> true
    }

    override fun hashCode() =
        Objects.hash(Card::class, name, type, maxHp)

    override fun toString() =
        "Card(name: '$name', type: '$type', maxHp: $maxHp)"
}
