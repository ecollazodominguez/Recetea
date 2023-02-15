type DataType = {
  time: string;
  quantity: string;
};

interface IPropsRecipe {
  title: string;
  data: DataType;
  description: string;
  img: string;
  className: string;
}

interface IPropsImageContent {
  img: string;
  className: string;
}

interface IPropsTextContent {
  title: string;
  data: DataType;
  description: string;
  className: string;
}

export const RecipeCard = ({
  title,
  data,
  img,
  description,
  className,
}: IPropsRecipe) => {
  return (
    <article className={`${className}-article`}>
      <RecipeImageContent img={img} className={className} />
      <RecipeTextContent
        title={title}
        data={data}
        description={description}
        className={className}
      />
    </article>
  );
};

export const RecipeImageContent = ({ img, className }: IPropsImageContent) => {
  return (
    <section className={`${className}-imageContent`}>
      <img className={`${className}-img`} src={img} alt="" />
      <img className={`${className}-Like`} src="empty-star-svg.svg" alt="" />
    </section>
  );
};

export const RecipeTextContent = ({
  title,
  data,
  description,
  className,
}: IPropsTextContent) => {
  return (
    <section className={`${className}-textContent`}>
      <h2 className={`${className}-recipeTitle`}>{title}</h2>
      <ul className={`${className}-recipeDataUl`}>
        <li className={`${className}-recipeDataLi`}>{data.time}</li>
        <li className={`${className}-recipeDataLi`}>{data.quantity}.</li>
      </ul>
      {className === "LatestRecipeCard" ? (
        <p className={`LatestRecipeCard-recipeDescription`}>{description}</p>
      ) : (
        ""
      )}
    </section>
  );
};
