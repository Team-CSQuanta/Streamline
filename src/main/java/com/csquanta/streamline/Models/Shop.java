package com.csquanta.streamline.Models;

import java.io.Serializable;
import java.util.TreeSet;

public class Shop implements Serializable {
    private final TreeSet<Armor> armorsList = new TreeSet<>(new ArmorComp());

    public TreeSet<Armor> getArmorsList() {
        return armorsList;
    }

    private void addArmors(Armor armor){
        armorsList.add(armor);
    }
    private void removeArmor(Armor armor){
        armorsList.remove(armor);
    }
    public TreeSet<Armor> firstInitializeArmor(){
        // adding healer armor
        armorsList.add(new Armor("/Images/gear/armor/Healer/healer_1.png", "Healer one", "100"));
        armorsList.add(new Armor("/Images/gear/armor/Healer/healer_2.png", "Healer two", "200"));
        armorsList.add(new Armor("/Images/gear/armor/Healer/healer_3.png", "Healer three", "300"));
        armorsList.add(new Armor("/Images/gear/armor/Healer/healer_4.png", "Healer four", "400"));
        armorsList.add(new Armor("/Images/gear/armor/Healer/healer_5.png", "Healer five", "500"));
        // adding Rogue armor
        armorsList.add(new Armor("/Images/gear/armor/Rogue/rogue_1.png", "Rogue One", "100"));
        armorsList.add(new Armor("/Images/gear/armor/Healer/healer_2.png", "Rogue two", "200"));
        armorsList.add(new Armor("/Images/gear/armor/Healer/healer_3.png", "Rogue three", "300"));
        armorsList.add(new Armor("/Images/gear/armor/Healer/healer_4.png", "Rogue four", "400"));
        armorsList.add(new Armor("/Images/gear/armor/Healer/healer_5.png", "Rogue five", "500"));
        // Adding Specials
        armorsList.add(new Armor("/Images/gear/armor/Specials/bardRobes.png", "Bard Robes", "600"));
        armorsList.add(new Armor("/Images/gear/armor/Specials/dandySuit.png", "Dandy Suit", "600"));
        armorsList.add(new Armor("/Images/gear/armor/Specials/finnedOceanicArmor.png", "Oceanic Armor", "600"));
        armorsList.add(new Armor("/Images/gear/armor/Specials/lunarWarriorArmor.png", "Lunar Warrior", "600"));
        armorsList.add(new Armor("/Images/gear/armor/Specials/mammothRiderArmor.png", "Mammoth Rider Armor", "600"));


        armorsList.add(new Armor("/Images/gear/armor/Specials/nomadsCuirass.png", "Nomads Cuirass", "600"));
        armorsList.add(new Armor("/Images/gear/armor/Specials/pyromancersRobes.png", "Pyromancers Robes", "600"));
        armorsList.add(new Armor("/Images/gear/armor/Specials/roguishRainbowMessengerRobes.png", "Roguish Rainbow Robes", "600"));
        armorsList.add(new Armor("/Images/gear/armor/Specials/samuraiArmor.png", "Samurai Armor", "600"));
        armorsList.add(new Armor("/Images/gear/armor/Specials/sneakthiefRobes.png", "Sneak thief Robes", "600"));
        armorsList.add(new Armor("/Images/gear/armor/Specials/snowSovereignRobes.png", "Snow Sovereign Robes", "600"));
        return armorsList;
    }
}
