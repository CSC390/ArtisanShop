import React from "react";
import Layout from "../../../components/Admin/Layout";
import ProductForm from "../../../components/Admin/ProductForm";

export default function NewProduct() {
  return (
    <Layout>
      <h1 className="mt-4 text-center">Create New Product</h1>
      <div className="container mt-4">
        <div className="row">
          <div className="">
            <ProductForm />
          </div>
        </div>
      </div>
    </Layout>
  );
}
