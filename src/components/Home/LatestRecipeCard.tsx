interface IPropClassName {
  className: string;
}

export const LatestRecipeCard = () => {
  return (
    <section className="LatestRecipeCard">
      <article className="LatestRecipeCard-article">
        <RecipeImageContent className="LatestRecipeCard" />
        <RecipeTextContent className="LatestRecipeCard" />
      </article>
    </section>
  );
};

export const RecipeImageContent = ({ className }: IPropClassName) => {
  return (
    <section className={`${className}-imageContent`}>
      <img className={`${className}-img`} src="ReceteaWeb.png" alt="" />
      <img className={`${className}-Like`} src="empty-star-svg.svg" alt="" />
    </section>
  );
};

export const RecipeTextContent = ({ className }: IPropClassName) => {
  return (
    <section className={`${className}-textContent`}>
      <h2 className={`${className}-recipeTitle`}>Título de receta</h2>
      <ul className={`${className}-recipeDataUl`}>
        <li className={`${className}-recipeDataLi`}>120 min.</li>
        <li className={`${className}-recipeDataLi`}>5 pers.</li>
      </ul>
      {className === "LatestRecipeCard" ? (
        <p className={`LatestRecipeCard-recipeDescription`}>
          Lorem ipsum dolor, sit amet consectetur adipisicing elit. Rem dicta
          dignissimos
        </p>
      ) : (
        ""
      )}
    </section>
  );
};
