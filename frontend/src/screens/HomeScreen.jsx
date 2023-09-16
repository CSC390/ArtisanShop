import Categories from "../components/Categories";
import ShopBar from "../components/ShopBar";
import FeaturedStart from "../components/FeaturedStart";
import SubscribeSection from "../components/SubscribeSection";

const HomeScreen = () => {
  return (
    <>
      <div className="container-fluid mb-5">
        <div className="row border-top px-xl-5">
          <Categories />
          <ShopBar />
          <FeaturedStart />
          <SubscribeSection />
        </div>
      </div>
    </>
  );
};

export default HomeScreen;
