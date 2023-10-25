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
    <main className="bg-whitesmoke">
      <div className="container">
        <div className="sc-wrapper py-5">
          <Title title={categoryKey} />
          {categoryLoading ? (
            <Loader />
          ) : (
            <>
              {categoryProducts.length === 0 ? (
                <p className="text-center p-xxl-5 fs-26">
                  No products available in this category.
                </p>
              ) : (
                <ProductList products={categoryProducts} />
              )}
            </>
          )}
        </div>
      </div>
    </main>
  );
};

export default CategoryProductListPage;
