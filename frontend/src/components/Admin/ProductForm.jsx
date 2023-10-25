import React, { useEffect, useState } from "react";
// import { useRouter } from "next/router";
import axios from "axios";
// import { ReactSortable } from "react-sortablejs";
import Loader from "../Loader";
import { axiosCategories, axiosProducts, axiosUpload } from "../../api/axios";

export default function ProductForm({
  _id,
  title: existingTitle,
  description: existingDescription,
  price: existingPrice,
  images: existingImages,
  category: assignedCategory,
  properties: assignedProperties,
}) {
  const [title, setTitle] = useState(existingTitle || "");
  const [description, setDescription] = useState(existingDescription || "");
  const [category, setCategory] = useState(assignedCategory || "");
  const [productProperties, setProductProperties] = useState(
    assignedProperties || {}
  );
  const [price, setPrice] = useState(existingPrice || "");
  const [images, setImages] = useState(existingImages || []);
  const [goToProducts, setGoToProducts] = useState(false);
  const [isUploading, setIsUploading] = useState(false);
  const [categories, setCategories] = useState([]);
  //   const router = useRouter();

  useEffect(() => {
    axiosCategories.get("getCategoriesIds").then((result) => {
      setCategories(result.data);
    });
  }, []);

  async function saveProduct(ev) {
    ev.preventDefault();
    const data = {
      title,
      description,
      price,
      images,
      category,
      properties: productProperties,
    };
    if (_id) {
      // Update
      await axios.put("/api/products", { ...data, _id });
    } else {
      // Create
      await axios.post("/api/products", data);
    }
    setGoToProducts(true);
  }

  if (goToProducts) {
    axiosProducts.push("getAll");
  }

  async function uploadImages(ev) {
    const files = ev.target?.files;
    if (files?.length > 0) {
      setIsUploading(true);
      const data = new FormData();
      for (const file of files) {
        data.append("file", file);
      }
      const res = await axiosUpload.post("upload", data);
      console.log(data);
      setImages((oldImages) => {
        return [...oldImages, ...res.data.links];
      });
      setIsUploading(false);
    }
  }

  function updateImagesOrder(images) {
    setImages(images);
  }

  function setProductProp(propName, value) {
    setProductProperties((prev) => {
      const newProductProps = { ...prev };
      newProductProps[propName] = value;
      return newProductProps;
    });
  }

  return (
    <form onSubmit={saveProduct}>
      <div className="mb-3">
        <label htmlFor="productName" className="form-label">
          Product name
        </label>
        <input
          type="text"
          className="form-control"
          id="productName"
          placeholder="Product name"
          value={title}
          onChange={(ev) => setTitle(ev.target.value)}
        />
      </div>

      <div className="mb-3">
        <label htmlFor="productBrand" className="form-label">
          Product Brand
        </label>
        <input
          type="text"
          className="form-control"
          id="productBrand"
          placeholder="Product Brand"
          value={title}
          onChange={(ev) => setTitle(ev.target.value)}
        />
      </div>

      <div className="mb-3">
        <label htmlFor="category" className="form-label">
          Category
        </label>
        <select
          className="form-select"
          id="category"
          value={category}
          onChange={(ev) => setCategory(ev.target.value)}
        >
          <option value="">Uncategorized</option>
          {categories.length > 0 &&
            categories.map((c) => (
              <option key={c.id} value={c.id}>
                {c.categoryName}
              </option>
            ))}
        </select>
      </div>

      <div className="mb-3">
        <label className="form-label">Photos</label>
        <div className="mb-2 d-flex flex-wrap gap-1">
          {/* <ReactSortable
          list={images}
          className="d-flex flex-wrap gap-1" setList={updateImagesOrder}> */}
          {!!images?.length &&
            images.map((link) => (
              <div
                key={link}
                className="h-24 bg-white p-4 shadow-sm rounded-sm border border-gray-200"
              >
                <img src={link} alt="" className="rounded-lg" />
              </div>
            ))}
          {/* </ReactSortable> */}
          {isUploading && (
            <div className="h-24 d-flex align-products-center">
              <Loader />
            </div>
          )}
          <label className="w-24 h-24 cursor-pointer text-center d-flex flex-column align-products-center justify-content-center text-sm gap-1 text-primary rounded-sm bg-white shadow-sm border border-primary">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth={1.5}
              stroke="currentColor"
              className="w-6 h-6"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M3 16.5v2.25A2.25 2.25 0 005.25 21h13.5A2.25 2.25 0 0021 18.75V16.5m-13.5-9L12 3m0 0l4.5 4.5M12 3v13.5"
              />
            </svg>
            <div>Add image</div>
            <input
              type="file"
              onChange={uploadImages}
              className="visually-hidden"
            />
          </label>
        </div>
      </div>

      <div className="mb-3">
        <label htmlFor="description" className="form-label">
          Description
        </label>
        <textarea
          className="form-control"
          id="description"
          placeholder="Description"
          value={description}
          onChange={(ev) => setDescription(ev.target.value)}
        />
      </div>

      <div className="mb-3">
        <label htmlFor="price" className="form-label">
          Price (in USD)
        </label>
        <input
          type="number"
          className="form-control"
          id="price"
          placeholder="Price"
          value={price}
          onChange={(ev) => setPrice(ev.target.value)}
        />
      </div>

      <button type="submit" className="btn btn-primary">
        Save
      </button>
    </form>
  );
}
