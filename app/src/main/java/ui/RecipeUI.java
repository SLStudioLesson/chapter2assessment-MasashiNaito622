package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

import data.RecipeFileHandler;

public class RecipeUI {
    private BufferedReader reader;
    private RecipeFileHandler fileHandler;

    public RecipeUI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fileHandler = new RecipeFileHandler();
    }

    public RecipeUI(BufferedReader reader, RecipeFileHandler fileHandler) {
        this.reader = reader;
        this.fileHandler = fileHandler;
    }

    public void displayMenu() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        // 設問1: 一覧表示機能
                        displayRecipes();//1を選択で一覧表示のプログラムに遷移
                        break;
                    case "2":
                        // 設問2: 新規登録機能
                        addNewRecipe();//2を選択で新規登録の機能に遷移
                        break;
                    case "3":
                        // 設問3: 検索機能
                        break;
                    case "4":
                        System.out.println("Exit the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    /**
     * 設問1: 一覧表示機能
     * RecipeFileHandlerから読み込んだレシピデータを整形してコンソールに表示します。
     */
    private void displayRecipes() {
        //RecipeFileHandlerのリスト化したtxtファイルを読み込む
        ArrayList<String> recipes = fileHandler.readRecipes();

        //レシピデータが空の場合はNo recipes available. というメッセージを表示
        if(recipes.isEmpty()){
            System.out.println("No recipes available.");
            return;
        }

        //レシピデータを並べて表示する
        System.out.println("Recipes:");
        System.out.println("-----------------------------------");
        //String型の拡張for文を使用し、レシピデータの数だけ表示書き込みを行う
        for(String recipe:recipes){
            //「,」を使用し、分割する
            String[] number = recipe.split(",", 2);
            String Recipe_Name = number[0];
            String Main_Ingredients;//主な材料
            if(number.length > 1){
                Main_Ingredients = number[1];
            
            System.out.println("Recipe Name: " + Recipe_Name);
            System.out.println("Main Ingredients: " + Main_Ingredients);
            System.out.println("-----------------------------------");
            }
        }

    }

    /**
     * 設問2: 新規登録機能
     * ユーザーからレシピ名と主な材料を入力させ、RecipeFileHandlerを使用してrecipes.txtに新しいレシピを追加します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void addNewRecipe() throws IOException {
        //レシピ名の入力をする
        System.out.print("Enter recipe name: ");
        String recipeName = reader.readLine();

        //材料を入力する
        System.out.print("Enter main ingredients (comma separated):");
        String ingredients = reader.readLine();
        //RecipFileHandlerを使用してレシピと材料を追加する
        fileHandler.addRecipe(recipeName, ingredients);

        //入力を成功した場合の出力
        System.out.println("Recipe added successfully");


    }

    /**
     * 設問3: 検索機能
     * ユーザーから検索クエリを入力させ、そのクエリに基づいてレシピを検索し、一致するレシピをコンソールに表示します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void searchRecipe() throws IOException {

    }

}

