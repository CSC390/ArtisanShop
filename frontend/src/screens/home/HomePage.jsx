import "../../styles/HomePage.css";
import "../../App.css";
import { Title, ProductList, FilterView, Loader } from "../../components";
import { ProductContext } from "../../context/productContext";
import { FilterContext } from "../../context/filterContext";
import { useContext } from "react";

import { ToastContainer } from "react-toastify";

const HomePage = () => {
  const { productsLoading } = useContext(ProductContext);
  const { filtered_products } = useContext(FilterContext);
  console.log(filtered_products);

  return (
    <main className="bg-whitesmoke">
      <section className="sc-wrapper py-5">
        <Title title={"All Our Products"} />
        {productsLoading ? (
          <Loader />
        ) : (
          <div>
            <FilterView />
            <br />
            <br />
            <ProductList products={filtered_products} />
          </div>
        )}
      </section>

      <ToastContainer />
    </main>
  );
};

export default HomePage;
