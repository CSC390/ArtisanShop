import { useContext, useEffect } from "react";
import { useParams } from "react-router-dom";
import { Loader, ProductList, Title } from "../../components";
import { CategoryContext } from "../../context/categoryContext";

const CategoryProductListPage = () => {
  const { categoryKey } = useParams();
  const { getCategoryProducts, categoryProducts, dispatch, categoryLoading } =
    useContext(CategoryContext);

  useEffect(() => {
    getCategoryProducts(dispatch, categoryKey);
    // eslint-disable-next-line
  }, [categoryKey]);

  return (
    <main className="">
      <div className="container">
        <div className="sc-wrapper py-5">
          <Title title={categoryKey} />
          {categoryLoading ? (
            <Loader />
          ) : (
            <ProductList products={categoryProducts} />
          )}
        </div>
      </div>
    </main>
  );
};

export default CategoryProductListPage;
