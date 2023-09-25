import Categories from "../components/Sidebar/Categories";
import ShopBar from "../components/Carousel/ShopBar";
import FeaturedStart from "../components/FeaturedStart";
import SubscribeSection from "../components/SubscribeSection";
import HomeScreenProducts from "../components/HomeScreenProducts";

const HomeScreen = () => {
  return (
    <>
      <div className="container-fluid mb-5">
        <div className="row border-top px-xl-5">
          <Categories />
          <ShopBar />
          <FeaturedStart />
          {/* <SubscribeSection /> */}
          <HomeScreenProducts />
        </div>
      </div>
    </>
  );
};

export default HomeScreen;
