package cl.ravenhill.poca

import java.util.Objects
import kotlin.math.max
import kotlin.math.min

/**
 * A card in the game.
 *
 * @property hp The current health points of the card.
 *
 * @param name      The name of the card.
 * @param type      The type of the card.
 * @param maxHp     The maximum amount of health points the card can have.
 * @param attack    The amount of damage the card can deal.
 */
class Card(val name: String, val type: String, val maxHp: Int, val attack: Int) {
    var hp: Int = maxHp
        private set(newHp) {
            field = max(0, min(newHp, maxHp))
        }

    /** Attack another card.    */
    fun attack(opponent: Card) {
        if (!this.isKo()) {
            opponent.hp -= attack
        }
    }

    /** Check if the card is KO.    */
    fun isKo() = hp == 0

    override fun equals(other: Any?) = when {
        this === other -> true
        other !is Card -> false
        Card::class != other::class -> false
        name != other.name -> false
        type != other.type -> false
        maxHp != other.maxHp -> false
        attack != other.attack -> false
        else -> true
    }

    override fun hashCode() =
        Objects.hash(Card::class, name, type, maxHp)

    override fun toString() =
        """Card 
            | name: '$name', 
            | type: '$type', 
            | maxHp: $maxHp,
            | attack: $attack""".trimMargin()
}
