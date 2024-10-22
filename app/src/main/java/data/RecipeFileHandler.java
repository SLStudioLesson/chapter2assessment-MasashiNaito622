package data;

import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;//設問使用するため追加
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

//recipes.txtより、レシピを読み込む為の機能を持つクラス

public class RecipeFileHandler {
    private String filePath;

    public RecipeFileHandler() {
        filePath = "app/src/main/resources/recipes.txt";
    }

    public RecipeFileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 設問1: 一覧表示機能
     * recipes.txtからレシピデータを読み込み、それをリスト形式で返します。 <br> 
     * IOExceptionが発生したときは<i>Error reading file: 例外のメッセージ</i>とコンソールに表示します。
     *
     * @return レシピデータ
     */
    public ArrayList<String> readRecipes() {
        //レシピデータを読みだしリスト化するため、新しいリストを作成する
        ArrayList<String> recipes = new ArrayList<>();
        // ファイルを読み込む為に BufferedReaderを使用
        try(BufferedReader br = new BufferedReader(new FileReader(filePath));){
            String Write_a_line;
            //ファイルの各行を読み込み　記入がなければ記入する
            while((Write_a_line = br.readLine())!=null){
                recipes.add(Write_a_line);

            }

        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
        }
        //nullではなく、読みだしたリストを返す必要がある
        return recipes;
    }

    /**
     * 設問2: 新規登録機能
     * 新しいレシピをrecipes.txtに追加します。<br>
     * レシピ名と材料はカンマ区切りで1行としてファイルに書き込まれます。
     *
     * @param recipeName レシピ名
     * @param ingredients 材料名
     */
     // 
    public void addRecipe(String recipeName, String ingredients) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(recipeName + "," + ingredients); // レシピ名と材料をカンマ区切りで書き込む
            writer.newLine(); // 新しい行を追加
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
