import { Header } from "../components/Header/Header";
import { RecipeCard } from "../components/RecipeCard";
import { RecipeList } from "../components/RecipeList";

export const Home = () => {
  //Test purposes
  const recipe = {
    title: "Pollo al ajillo",
    data: { time: "120 min.", quantity: "5 pers" },
    description:
      "Lorem ipsum dolor sit, amet consectetur adipisicing elit. Officiis laboriosam assumenda mollitia itaque a atque.",
    img: "ReceteaWeb.png",
  };

  return (
    <>
      <Header />
      <main>
        <section className="LatestRecipeCard">
          <RecipeCard
            title={recipe.title}
            data={recipe.data}
            description={recipe.description}
            img={recipe.img}
            className="LatestRecipeCard"
          />
        </section>
        <RecipeList recipesTypeTitle="Recetas fáciles" />
        <RecipeList recipesTypeTitle="Recetas Veganas" />
      </main>
    </>
  );
};
