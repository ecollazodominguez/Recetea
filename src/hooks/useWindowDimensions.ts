import { useState, useEffect } from "react";
import internal from "stream";

interface IDimensions {
  width: number;
  height: number;
}

const getWindowDimensions = (): IDimensions => {
  const { innerWidth: width, innerHeight: height } = window;
  return {
    width,
    height,
  };
};

export const useWindowDimensions = (): IDimensions => {
  const [windowDimensions, setWindowDimensions] = useState(
    getWindowDimensions()
  );

  useEffect(() => {
    const handleWindowResize = () => {
      setWindowDimensions(getWindowDimensions());
    };

    window.addEventListener("resize", handleWindowResize);
    return () => window.removeEventListener("resize", handleWindowResize);
  }, []);

  return windowDimensions;
};
