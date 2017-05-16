/*
 * Copyright (C) 2015-2017 Daniel Saukel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.dre2n.commons.item;

/**
 * @author Daniel Saukel
 */
public enum ArmorSlot {

    MAIN_HAND("mainhand"),
    OFF_HAND("offhand"),
    HEAD("head"),
    TORSO("torso"),
    LEGS("legs"),
    FEET("feet");

    private String internalName;

    ArmorSlot(String internalName) {
        this.internalName = internalName;
    }

    public String getInternalName() {
        return internalName;
    }

}
