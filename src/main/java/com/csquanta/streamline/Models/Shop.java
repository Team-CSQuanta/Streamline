package com.csquanta.streamline.Models;

import com.csquanta.streamline.Controllers.ShopController;

import java.io.*;
import java.util.HashSet;
import java.util.TreeSet;

public class Shop implements Serializable {
    private final TreeSet<Item> armorsList = new TreeSet<>(new ItemComparator());
    private final HashSet<Item> boughtArmorList = new HashSet<>();
    private final HashSet<Item> boughtHeadWearList = new HashSet<>();
    private final HashSet<Item> boughtPetList = new HashSet<>();

    public HashSet<Item> getBoughtPetList() {
        return boughtPetList;
    }

    public void addPetToBoughtList(Item item){
        boughtPetList.add(item);
        petsList.remove(item);
    }
    public void removePetFromBoughtList(Item item){
        boughtPetList.remove(item);
        petsList.add(item);
    }

    public HashSet<Item> getBoughtHeadWearList() {
        return boughtHeadWearList;
    }
    public void addHeadWearToBoughtList(Item item){
        boughtHeadWearList.add(item);
        headWearList.remove(item);
    }
    public void removeHeadWearFromBoughtList(Item item){
        boughtHeadWearList.remove(item);
    }
    public HashSet<Item> getBoughtArmorList(){
        return boughtArmorList;
    }
    public void addArmorToBoughtList(Item item){
        boughtArmorList.add(item);
        armorsList.remove(item);
    }
    public void removeArmorFromBoughtList(Item item){
        boughtArmorList.remove(item);
        armorsList.add(item);
    }
    public TreeSet<Item> getArmorsList() {
        return armorsList;
    }
    public void addArmors(Item item){
        armorsList.add(item);
    }
    public void removeArmor(Item item){
        armorsList.remove(item);
    }
    public Shop(){
        firstPetInitialize();
        firstInitializeBackgrounds();
        firstInitializeHeadWear();
        firstInitializeArmor();
        addDefaultBackgrounds();
    }

    public TreeSet<Item> firstInitializeArmor(){
        // adding healer armor
        armorsList.add(new Item("/Images/gear/armor/Healer/healer_1.png", "Healer one", "100", "Armor"));
        armorsList.add(new Item("/Images/gear/armor/Healer/healer_2.png", "Healer two", "200", "Armor"));
        armorsList.add(new Item("/Images/gear/armor/Healer/healer_3.png", "Healer three", "300", "Armor"));
        armorsList.add(new Item("/Images/gear/armor/Healer/healer_4.png", "Healer four", "400", "Armor"));
        armorsList.add(new Item("/Images/gear/armor/Healer/healer_5.png", "Healer five", "500", "Armor"));
        // adding Rogue armor
        armorsList.add(new Item("/Images/gear/armor/Rogue/rogue_1.png", "Rogue One", "100", "Armor"));
        armorsList.add(new Item("/Images/gear/armor/Healer/healer_2.png", "Rogue two", "200", "Armor"));
        armorsList.add(new Item("/Images/gear/armor/Healer/healer_3.png", "Rogue three", "300", "Armor"));
        armorsList.add(new Item("/Images/gear/armor/Healer/healer_4.png", "Rogue four", "400", "Armor"));
        armorsList.add(new Item("/Images/gear/armor/Healer/healer_5.png", "Rogue five", "500", "Armor"));
        // Adding Specials
        armorsList.add(new Item("/Images/gear/armor/Specials/dandySuit.png", "Dandy Suit", "600", "Armor"));
        armorsList.add(new Item("/Images/gear/armor/Specials/finnedOceanicArmor.png", "Oceanic Armor", "600", "Armor"));
        armorsList.add(new Item("/Images/gear/armor/Specials/lunarWarriorArmor.png", "Lunar Warrior", "600", "Armor"));
        armorsList.add(new Item("/Images/gear/armor/Specials/mammothRiderArmor.png", "Mammoth Rider Armor", "600", "Armor"));


        armorsList.add(new Item("/Images/gear/armor/Specials/nomadsCuirass.png", "Nomads Cuirass", "600", "Armor"));
        armorsList.add(new Item("/Images/gear/armor/Specials/pyromancersRobes.png", "Pyromancers Robes", "600", "Armor"));
        armorsList.add(new Item("/Images/gear/armor/Specials/roguishRainbowMessengerRobes.png", "Roguish Rainbow Robes", "600", "Armor"));
        armorsList.add(new Item("/Images/gear/armor/Specials/samuraiArmor.png", "Samurai Armor", "600", "Armor"));
        armorsList.add(new Item("/Images/gear/armor/Specials/snowSovereignRobes.png", "Snow Sovereign Robes", "600", "Armor"));
        return armorsList;
    }
    private final TreeSet<Item> headWearList = new TreeSet<>(new ItemComparator());

    public TreeSet<Item> getHeadWearList() {
        return headWearList;
    }
    public TreeSet<Item> firstInitializeHeadWear(){
        headWearList.add(new Item("/Images/gear/head/head_healer_1.png", "Head Healer One", "100", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_healer_2.png", "Head Healer Two", "200", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_healer_3.png", "Head Healer Three", "300", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_healer_4.png", "Head Healer Four", "400", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_healer_5.png", "Head Healer Five", "500", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_special_2.png", "Head Special Two", "600", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_special_bardHat.png", "Special Bard Hat", "600", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_special_clandestineCowl.png", "Clandestine Cowl", "600", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_special_dandyHat.png", "Dandy Hat", "600", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_special_fireCoralCirclet.png", "Fire Coral Circlet", "600", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_special_kabuto.png", "Special Kabuto", "600", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_special_lunarWarriorHelm.png", "Lunar Warrior Helm", "600", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_special_mammothRiderHelm.png", "Mammoth Rider Helm", "600", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_special_pyromancersTurban.png", "Pyromancers Turban", "600", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_special_roguishRainbowMessengerHood.png", "Roguish Rainbow Hood", "600", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_special_snowSovereignCrown.png", "Sovereign Crown", "600", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_special_spikedHelm.png", "Spiked Helm", "600", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_special_turkeyHelmBase.png", "Turkey Helm Base", "600", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_special_turkeyHelmGilded.png", "Turkey Helm Gilded", "600", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_warrior_1.png", "Head Warrior One", "100", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_warrior_2.png", "Head Warrior Two", "200", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_warrior_3.png", "Head Warrior Three", "300", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_warrior_4.png", "Head Warrior Four", "400", "Head Wear"));
        headWearList.add(new Item("/Images/gear/head/head_warrior_5.png", "Head Warrior Five", "500", "Head Wear"));
        return headWearList;
    }
    private final TreeSet<Item> backgroundsList = new TreeSet<>(new ItemComparator());
    private final HashSet<Item> boughtBackgroundList = new HashSet<>();

    public void addDefaultBackgrounds(){
        boughtBackgroundList.add(new Item("/Images/backgrounds/Defaults/background_blue.png", "DefaultBackground", "NoPrice", "Backgrounds"));
        boughtBackgroundList.add(new Item("/Images/backgrounds/Defaults/background_green.png", "DefaultBackground", "NoPrice", "Backgrounds"));
        boughtBackgroundList.add(new Item("/Images/backgrounds/Defaults/background_red.png", "DefaultBackground", "NoPrice", "Backgrounds"));
        boughtBackgroundList.add(new Item("/Images/backgrounds/Defaults/background_violet.png", "DefaultBackground", "NoPrice", "Backgrounds"));
        boughtBackgroundList.add(new Item("/Images/backgrounds/Defaults/background_yellow.png", "DefaultBackground", "NoPrice", "Backgrounds"));
    }

    public HashSet<Item> getBoughtBackgroundList() {
        return boughtBackgroundList;
    }
    public void addBackgroundToBoughtList(Item item){
        boughtBackgroundList.add(item);
        backgroundsList.remove(item);
    }
    public void removeBackgroundFrommBoughtList(Item item){
        boughtBackgroundList.remove(item);
        backgroundsList.add(item);
    }
    public TreeSet<Item> getBackgroundsList(){
        return backgroundsList;
    }
    public void addBackgrounds(Item item){
        backgroundsList.add(item);
    }
    public void removeBackgrounds(Item item){
        backgroundsList.remove(item);
    }
    public TreeSet<Item> firstInitializeBackgrounds(){
        backgroundsList.add(new Item("/Images/backgrounds/background_at_the_docks.png", "At the docks", "800", "Backgrounds"));
        backgroundsList.add(new Item("/Images/backgrounds/background_aurora.png", "Aurora", "1000", "Backgrounds"));
        backgroundsList.add(new Item("/Images/backgrounds/background_autumn_bridge.png", "Autumn bridge", "900", "Backgrounds"));
        backgroundsList.add(new Item("/Images/backgrounds/background_autumn_lakeshore.png", "Autumn lakeshore", "900", "Backgrounds"));
        backgroundsList.add(new Item("/Images/backgrounds/background_boardwalk_into_sunset.png", "Boardwalk into sunset", "1000", "Backgrounds"));
        backgroundsList.add(new Item("/Images/backgrounds/background_cherry_trees.png", "Cherry Trees", "1000", "Backgrounds"));
        backgroundsList.add(new Item("/Images/backgrounds/background_cozy_bedroom.png", "Cozy Bedroom", "900", "Backgrounds"));
        backgroundsList.add(new Item("/Images/backgrounds/background_daytime_misty_forest.png", "Misty Forest", "950", "Backgrounds"));
        backgroundsList.add(new Item("/Images/backgrounds/background_distant_castle.png", "Castle", "900", "Backgrounds"));
        backgroundsList.add(new Item("/Images/backgrounds/background_elegant_ballroom.png", "Elegant Ballroom", "1000", "Backgrounds"));
        backgroundsList.add(new Item("/Images/backgrounds/background_elegant_garden.png", "Elegant Garden", "900", "Backgrounds"));
        backgroundsList.add(new Item("/Images/backgrounds/background_enchanted_music_room.png", "Music Room", "1000", "Backgrounds"));
//        backgroundsList.add(new Item("/Images/backgrounds/background_flying_over_an_autumn_forest.png", "Autumn Forest", "1000", "Backgrounds"));
//        backgroundsList.add(new Item("/Images/backgrounds/background_fortune_tellers_shop.png", "Fortune tellers house", "900"));
//        backgroundsList.add(new Item("/Images/backgrounds/background_gazebo.png", "Gazebo", "1000"));
//        backgroundsList.add(new Item("/Images/backgrounds/background_grand_staircase.png", "Grand Staircase", "900"));
//        backgroundsList.add(new Item("/Images/backgrounds/background_hall_of_heroes.png", "Hall of heroes", "1000"));
//        backgroundsList.add(new Item("/Images/backgrounds/background_in_a_classroom.png", "In a Classroom", "1000"));
//        backgroundsList.add(new Item("/Images/backgrounds/background_in_front_of_fountain.png", "Front of fountain", "800"));
//        backgroundsList.add(new Item("/Images/backgrounds/background_magical_museum.png", "Magical Museum", "800"));
//        backgroundsList.add(new Item("/Images/backgrounds/background_mist_shrouded_mountain.png", "Mountain", "1000"));
//        backgroundsList.add(new Item("/Images/backgrounds/background_misty_autumn_forest.png", "Misty autumn forest", "800"));
//        backgroundsList.add(new Item("/Images/backgrounds/background_mountain_lake.png", "Mountain lake", "1000"));
//        backgroundsList.add(new Item("/Images/backgrounds/background_ocean_sunrise.png", "Ocean sunrise", "800"));
        backgroundsList.add(new Item("/Images/backgrounds/background_pagodas.png", "Pagodas", "1000", "Backgrounds"));
        backgroundsList.add(new Item("/Images/backgrounds/background_pyramids.png", "Pyramids", "800", "Backgrounds"));
        backgroundsList.add(new Item("/Images/backgrounds/background_rainbow_eucalyptus.png", "Eucalyptus", "1000", "Backgrounds"));
        backgroundsList.add(new Item("/Images/backgrounds/background_rainforest.png", "Rainforest", "800", "Backgrounds"));
//        backgroundsList.add(new Item("/Images/backgrounds/background_rowboat.png", "Rowboat", "900"));
        backgroundsList.add(new Item("/Images/backgrounds/background_sailboat_at_sunset.png", "Sailboat on sunset", "1000", "Backgrounds"));
//        backgroundsList.add(new Item("/Images/backgrounds/background_salt_lake.png", "Salt lake", "700"));
        backgroundsList.add(new Item("/Images/backgrounds/background_snowy_sunrise.png", "Snowy Sunrise", "1000", "Backgrounds"));
        backgroundsList.add(new Item("/Images/backgrounds/background_snowy_temple.png", "Snowy Temple", "950", "Backgrounds"));
//        backgroundsList.add(new Item("/Images/backgrounds/background_stained_glass.png", "Stained glass", "600"));
        backgroundsList.add(new Item("/Images/backgrounds/background_sunset_meadow.png", "Sunset meadow", "1000", "Backgrounds"));
        backgroundsList.add(new Item("/Images/backgrounds/background_sunset_oasis.png", "Sunset oasis", "900", "Backgrounds"));
        backgroundsList.add(new Item("/Images/backgrounds/background_treasure_room.png", "Treasure room", "1100", "Backgrounds"));
        backgroundsList.add(new Item("/Images/backgrounds/background_treehouse.png", "Treehouse", "1000", "Backgrounds"));
//        backgroundsList.add(new Item("/Images/backgrounds/background_twinkly_lights.png", "Twinkly lights", "900"));
//        backgroundsList.add(new Item("/Images/backgrounds/background_vineyard.png", "Vineyard", "900"));
        return backgroundsList;
    }
    private final TreeSet<Item> petsList = new TreeSet<>(new ItemComparator());
    public TreeSet<Item> getPetsList(){
        return petsList;
    }
    public void addPet(Item item){
        petsList.add(item);
    }
    public void removePet(Item item){
        petsList.remove(item);
    }
    public TreeSet<Item> firstPetInitialize(){
        petsList.add(new Item("/Images/stable/pets/Pet-BearCub-Red.png", "Bear Cub", "1500", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-Bunny-Base.png", "Bunny", "1500", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-Cow-Base.png", "Cow", "1500", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-Dolphin-CottonCandyBlue.png", "Dolphin", "2000", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-Falcon-Base.png", "Falcon", "1500", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-Fox-Red.png", "Fox", "1500", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-Gryphon-Base.png", "Gryphon", "1500", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-GuineaPig-Base.png", "Guinea Pig", "2000", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-Hippo-Base.png", "Hippo", "1500", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-Horse-Base.png", "Horse", "1500", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-Kangaroo-Base.png", "Kangaroo", "1500", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-LionCub-Base.png", "Lion cub", "1500", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-MagicalBee-Base.png", "Magical Bee", "1500", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-Mammoth-Base.png", "Mammoth", "1500", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-Monkey-Base.png", "Monkey", "1500", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-Orca-Base.png", "Orca", "2000", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-Owl-Base.png", "Owl", "2000", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-PandaCub-Base.png", "Panda cub", "1500", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-Parrot-Base.png", "Parrot", "2000", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-Penguin-Base.png", "Penguin", "2000", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-Rooster-Base.png", "Rooster", "2000", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-Squirrel-Base.png", "Squirrel", "1500", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-TigerCub-Red.png", "Tiger cub", "1500", "Pet"));
        petsList.add(new Item("/Images/stable/pets/Pet-Turkey-Base.png", "Turkey", "1500", "Pet"));

        return petsList;
    }

    public static void serializeShop(){
        try(ObjectOutputStream objOStream = new ObjectOutputStream(new FileOutputStream("Shop_Info"))){
            Shop shop = ShopController.getShop();
            objOStream.writeObject(shop);
        }catch (Exception e){
            System.out.println("Serialization failed");
        }
    }
    public static void deserializeShop(){
        try(ObjectInputStream objIStream = new ObjectInputStream(new FileInputStream("Shop_Info"))){
            ShopController.shop = (Shop) objIStream.readObject();

        }catch (Exception e){
            System.out.println("Deserialization failed");
            e.printStackTrace();
        }
    }
}
