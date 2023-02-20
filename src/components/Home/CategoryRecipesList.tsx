import { Link } from "react-router-dom";

export const CategoryRecipeList = () => {
  return (
    <section className="CategoryRecipeList">
      <h2 className="CategoryRecipeList-h3">RECETAS</h2>
      <ul className="CategoryRecipeList-ul">
        <li key="" className="CategoryRecipeList-li">
          <Link to="/Favoritas" className="CategoryRecipeList-link">
            Favoritas
          </Link>
        </li>
        <li key="" className="CategoryRecipeList-li">
          <Link to="/Pasta" className="CategoryRecipeList-link">
            Pasta
          </Link>
        </li>
        <li key="" className="CategoryRecipeList-li">
          <Link to="/Postres" className="CategoryRecipeList-link">
            Postres
          </Link>
        </li>
        <li key="" className="CategoryRecipeList-li">
          <Link to="/Vegano" className="CategoryRecipeList-link">
            Vegano
          </Link>
        </li>
        <li key="" className="CategoryRecipeList-li">
          <Link to="/Pescado" className="CategoryRecipeList-link">
            Pescado
          </Link>
        </li>
        <li key="" className="CategoryRecipeList-li">
          <Link to="/Carne" className="CategoryRecipeList-link">
            Carne
          </Link>
        </li>
        <li key="" className="CategoryRecipeList-li">
          <Link to="/Oriental" className="CategoryRecipeList-link">
            Oriental
          </Link>
        </li>
        <li key="" className="CategoryRecipeList-li">
          <Link to="/Mediterráneo" className="CategoryRecipeList-link">
            Mediterráneo
          </Link>
        </li>
        <li key="" className="CategoryRecipeList-li">
          <Link to="/Italiano" className="CategoryRecipeList-link">
            Italiano
          </Link>
        </li>
      </ul>
    </section>
  );
};
