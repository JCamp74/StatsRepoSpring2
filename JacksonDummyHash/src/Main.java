public class Main {
    public static void main(String[] args) {
        String[] fruits = {
            "Apple", "Banana", "Cherry", "Date", "Elderberry", "Fig", "Grape", "Honeydew",
            "Indian Fig", "Jackfruit", "Kiwi", "Lemon", "Mango", "Nectarine", "Orange", "Papaya",
            "Quince", "Raspberry", "Strawberry", "Tomato", "Ugli Fruit", "Vanilla Bean",
            "Watermelon", "Xigua", "Yam", "Zucchini", "Pineapple", "Blueberry", "Blackberry",
            "Apricot", "Coconut", "Pomegranate", "Persimmon", "Mulberry", "Guava", "Tangerine",
            "Cantaloupe", "Cranberry", "Passionfruit", "Lychee", "Boysenberry", "Starfruit",
            "Soursop", "Avocado", "Gooseberry", "Durian", "Plum", "Peach", "Pear", "Miracle Fruit",
            "Blackcurrant", "Redcurrant", "Whitecurrant", "Jabuticaba", "Longan", "Marang",
            "Mangosteen", "Sapodilla", "Salak", "Santol", "Surinam Cherry", "Bael", "Custard Apple",
            "Feijoa", "Nance", "Jujube", "Loquat", "Ackee", "Buddha's Hand", "Medlar", "Pomelo",
            "Rambutan", "Cupuacu", "Lucuma", "Horned Melon", "Sugar Apple", "Yuzu", "Calamansi",
            "Gac Fruit", "Mamey Sapote", "Limequat", "Cloudberry", "Bilberry", "Chokeberry",
            "Sea Buckthorn", "Mountain Pepper", "Kei Apple", "Muntingia", "Bignay", "Pawpaw"
        };

        for(int i = 1000; i < 21000; i += 1000) {
            JacksonDummyHash<String, Integer> jdm = new JacksonDummyHash<>();
            double time1 = System.currentTimeMillis();
            // Generate an i amount of unique fruit entries for testing.
            for (int j = 1; j <= i; j++) {
                String fruitName = fruits[j % fruits.length] + " " + j; // Unique fruit names
                jdm.put(fruitName, (int) (Math.random() * 1000 + 1)); // Random quantities
            }
            double time2 = System.currentTimeMillis();
            double totalTime = (time2-time1) / 1000;
            System.out.println("\nTotal time to run " + i + " entries: " + totalTime + " seconds");
        }
        
        
    }
}
