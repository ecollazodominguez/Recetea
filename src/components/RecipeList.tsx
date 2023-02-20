import { RecipeCard } from "./RecipeCard";

interface IPropTitle {
  recipesTypeTitle: string;
}
//Test purposes
const recipes = [
  {
    title: "Tarta de leche merengada con galletas de canela",
    data: { time: "120 min.", quantity: "5 pers" },
    description:
      "Lorem ipsum dolor sit, amet consectetur adipisicing elit. Officiis laboriosam assumenda mollitia itaque a atque.",
    img: "ReceteaWeb.png",
  },
  {
    title: "Pollo al ajillo",
    data: { time: "120 min.", quantity: "5 pers" },
    description:
      "Lorem ipsum dolor sit, amet consectetur adipisicing elit. Officiis laboriosam assumenda mollitia itaque a atque.",
    img: "ReceteaWeb.png",
  },
  {
    title: "Pollo al ajillo",
    data: { time: "120 min.", quantity: "5 pers" },
    description:
      "Lorem ipsum dolor sit, amet consectetur adipisicing elit. Officiis laboriosam assumenda mollitia itaque a atque.",
    img: "ReceteaWeb.png",
  },
  {
    title: "Pollo al ajillo",
    data: { time: "120 min.", quantity: "5 pers" },
    description:
      "Lorem ipsum dolor sit, amet consectetur adipisicing elit. Officiis laboriosam assumenda mollitia itaque a atque.",
    img: "ReceteaWeb.png",
  },
  {
    title: "Pollo al ajillo",
    data: { time: "120 min.", quantity: "5 pers" },
    description:
      "Lorem ipsum dolor sit, amet consectetur adipisicing elit. Officiis laboriosam assumenda mollitia itaque a atque.",
    img: "ReceteaWeb.png",
  },
];

export const RecipeList = ({ recipesTypeTitle }: IPropTitle) => {
  return (
    <section className="RecipeCardList">
      <h2 className="RecipeCardList-h2">{recipesTypeTitle}</h2>
      <ul className="RecipeCardList-ul">
        {recipes.map((recipe) => {
          return (
            <li key={recipe.title} className="RecipeCardList-li">
              <RecipeCard
                title={recipe.title}
                data={recipe.data}
                description={recipe.description}
                img={recipe.img}
                className="RecipeCard"
              />
            </li>
          );
        })}
      </ul>
    </section>
  );
};
