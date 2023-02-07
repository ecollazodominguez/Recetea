import { Link } from "react-router-dom";
import { useState } from "react";
import { useWindowDimensions } from "../../hooks/useWindowDimensions";

const islogged = false; //testing purposes
interface IProps {
  firstLinkName: string;
  firstURL: string;
  secondLinkName: string;
  secondURL: string;
}
interface IToggle {
  isToggle: boolean;
}

export const Header = () => {
  return (
    <header className="Header">
      <TopHeader />
    </header>
  );
};

const TopHeader = () => {
  const { height, width } = useWindowDimensions();
  return (
    <>
      <h1 className="Header-h1">Recetea</h1>
      {width > 1000 ? <DesktopNavMenu /> : <ToggleNavMenu />}
      <BottomHeader />
    </>
  );
};

const BottomHeader = () => {
  return (
    <nav className="Header-nav">
      <Link to="/" className="Header-link">
        Recetas Veganas
      </Link>
      <Link to="/" className="Header-link">
        Recetas Fáciles
      </Link>
    </nav>
  );
};

const DesktopNavMenu = () => {
  return (
    <nav className="Header-nav">
      {islogged ? (
        <NavLinks
          firstLinkName="Crear Receta"
          firstURL="/home"
          secondLinkName="Usuario"
          secondURL="/home"
        />
      ) : (
        <NavLinks
          firstLinkName="Register"
          firstURL="/home"
          secondLinkName="Login"
          secondURL="/home"
        />
      )}
    </nav>
  );
};

const NavLinks = ({
  firstLinkName,
  firstURL,
  secondLinkName,
  secondURL,
}: IProps) => {
  return (
    <>
      <Link to={firstURL} className="Header-link">
        {firstLinkName}
      </Link>
      <Link to={secondURL} className="Header-link">
        {secondLinkName}
      </Link>
    </>
  );
};

const ToggleNavMenu = () => {
  const [isToggleNavMenu, setIsToggleNavMenu] = useState(false);
  const handleDropdown = () => {
    setIsToggleNavMenu(!isToggleNavMenu);
  };
  return (
    <>
      <button className="Header-button" onClick={handleDropdown}>
        <img className="Header-img" src="menu.svg" alt="Menu SVG" />
      </button>
      <NavMenuContent isToggle={isToggleNavMenu} />
    </>
  );
};

const NavMenuContent = ({ isToggle }: IToggle) => {
  return (
    <section
      className={isToggle ? "Header-toggle is-expanded" : "Header-toggle"}
    >
      <h2 className="Header-h2">Receteado</h2>
      <nav className="Header-nav">
        <Link to="/" className="Header-link">
          Recetas
        </Link>
        <Link to="/" className="Header-link">
          Recetas
        </Link>
        <Link to="/" className="Header-link">
          Recetas
        </Link>
      </nav>
    </section>
  );
};
