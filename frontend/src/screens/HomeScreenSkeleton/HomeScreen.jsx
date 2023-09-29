import Categories from "../../components/Sidebar/Categories";
import ShopBar from "../../components/Carousel/ShopBar";
import FeaturedStart from "../../components/ServicesBanner/FeaturedStart";
import SubscribeSection from "../../components/NewsletterBanner/SubscribeSection";
import HomePage from "../home/HomePage";

const HomeScreen = () => {
  return (
    <>
      <div className="container-fluid mb-5">
        <div className="row border-top px-xl-5">
          <Categories />
          <ShopBar />
          <FeaturedStart />
          {/* <SubscribeSection /> */}
          <HomePage />
        </div>
      </div>
    </>
  );
};

export default HomeScreen;
