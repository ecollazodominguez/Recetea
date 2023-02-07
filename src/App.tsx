import { useEffect } from "react";
import { Route, Routes, useLocation } from "react-router-dom";
import { Home } from "./Pages/Home";

function App() {
  const GoUpWhenChangeLocation = () => {
    const location = useLocation();

    useEffect(() => {
      window.scrollTo(0, 0);
    }, [location.pathname]);

    return null;
  };

  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/*" element={<Home />} />
      </Routes>
      <GoUpWhenChangeLocation />
    </>
  );
}

export default App;
