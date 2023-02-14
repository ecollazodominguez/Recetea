import { Header } from "../components/Header/Header";
import { LatestRecipeCard } from "../components/Home/LatestRecipeCard";

export const Home = () => {
  return (
    <>
      <Header />
      <main>
        <LatestRecipeCard />
      </main>
    </>
  );
};
