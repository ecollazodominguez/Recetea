import { Link } from "react-router-dom";
import { useEffect, useRef, useState } from "react";
import { useWindowDimensions } from "../../hooks/useWindowDimensions";

const islogged = false; // Testing purposes

// Interface for props for component NavLinks
interface INavLinksProps {
  firstLinkName: string; // 1st Link Tag name
  firstURL: string; // 1st Link Tag where to go: "/something"
  secondLinkName: string;
  secondURL: string;
}

//Iinterface for props for toggle components
interface IToggleProps {
  isToggleNavMenu: boolean; // Is Expanded?
  setIsToggleNavMenu: Function; // Setter
}

export const Header = () => {
  return (
    <header className="Header">
      <TopHeader />
      <BottomHeader />
    </header>
  );
};

const TopHeader = () => {
  const { height, width } = useWindowDimensions();
  return (
    <section className="Header-top">
      <h1 className="Header-h1">Recetea</h1>
      {width > 1000 ? <DesktopNavMenu /> : <ToggleNavMenu />}
    </section>
  );
};

const DesktopNavMenu = () => {
  return (
    <nav className="Header-desktopNav">
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
}: INavLinksProps) => {
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
  // Make a boolean state to check if the menu is expanded or no
  const [isToggleNavMenu, setIsToggleNavMenu] = useState<boolean>(false);

  // Function on click to change boolean value
  const handleDropdown = (): void => {
    setIsToggleNavMenu(!isToggleNavMenu);
  };

  return (
    <>
      <button className="Header-button" onClick={handleDropdown}>
        <img className="Header-img" src="menu.svg" alt="Menu SVG" />
      </button>
      <NavMenuContent
        isToggleNavMenu={isToggleNavMenu}
        setIsToggleNavMenu={setIsToggleNavMenu}
      />
    </>
  );
};

const NavMenuContent = ({
  isToggleNavMenu,
  setIsToggleNavMenu,
}: IToggleProps) => {
  // Referencing the article on this component (The menu we wanto to expand and collapse)
  const node = useRef<HTMLElement>(null);

  // Function to check if the node referencing (article) is the same as what the mouse clicked (e.target)
  const clickOutsideDropdown = (e: Event): void => {
    // If it's not the case we change the value to false and the menu will collapse
    if (node.current && !node.current.contains(e.target as Node)) {
      setIsToggleNavMenu(false);
    }
    // If they are the same (meaning you clicked the article itself), menu will still be expanded
  };

  // UseEffect to use the previous function. Checks every time you click something.
  // Everytime the boolean changes, remove the old listener and make new ones.
  useEffect(() => {
    document.addEventListener("touchmove", clickOutsideDropdown);
    document.addEventListener("mousedown", clickOutsideDropdown);
    return () => {
      document.removeEventListener("touchmove", clickOutsideDropdown);
      document.removeEventListener("mousedown", clickOutsideDropdown);
    };
  }, [isToggleNavMenu]);

  // We added corresponding className depending if it is expanded or not and make the transition on SCSS.
  return (
    <section
      className={
        isToggleNavMenu ? "Header-toggle is-expanded" : "Header-toggle"
      }
    >
      <article
        ref={node}
        className={
          isToggleNavMenu
            ? "Header-toggleArticle is-expanded"
            : "Header-toggleArticle"
        }
      >
        <h2 className="Header-h2">Recetea</h2>
        <nav className="Header-navToggle">
          <Link to="/" className="Header-linkToggle">
            Recetas Veganas
          </Link>
          <Link to="/" className="Header-linkToggle">
            Recetas de Postre
          </Link>
          <Link to="/" className="Header-linkToggle">
            Recetas Fáciles
          </Link>
        </nav>
      </article>
    </section>
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
