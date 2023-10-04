import React, { useEffect, useState } from "react";
import axios from "axios";
import Layout from "../../components/Admin/Layout";
import { withSwal } from "react-sweetalert2";

function Categories({ swal }) {
  const [editedCategory, setEditedCategory] = useState(null);
  const [name, setName] = useState("");
  const [parentCategory, setParentCategory] = useState("");
  const [categories, setCategories] = useState([]);
  const [properties, setProperties] = useState([]);

  useEffect(() => {
    fetchCategories();
  }, []);

  function fetchCategories() {
    axios.get("/api/categories").then((result) => {
      setCategories(result.data);
    });
  }

  async function saveCategory(ev) {
    ev.preventDefault();
    const data = {
      name,
      parentCategory,
      properties: properties.map((p) => ({
        name: p.name,
        values: p.values.split(","),
      })),
    };
    if (editedCategory) {
      data._id = editedCategory._id;
      await axios.put("/api/categories", data);
      setEditedCategory(null);
    } else {
      await axios.post("/api/categories", data);
    }
    setName("");
    setParentCategory("");
    setProperties([]);
    fetchCategories();
  }

  function editCategory(category) {
    setEditedCategory(category);
    setName(category.name);
    setParentCategory(category.parent?._id);
    setProperties(
      category.properties.map(({ name, values }) => ({
        name,
        values: values.join(","),
      }))
    );
  }

  function deleteCategory(category) {
    swal
      .fire({
        title: "Are you sure?",
        text: `Do you want to delete ${category.name}?`,
        showCancelButton: true,
        cancelButtonText: "Cancel",
        confirmButtonText: "Yes, Delete!",
        confirmButtonColor: "#d55",
        reverseButtons: true,
      })
      .then(async (result) => {
        if (result.isConfirmed) {
          const { _id } = category;
          await axios.delete("/api/categories?_id=" + _id);
          fetchCategories();
        }
      });
  }

  function addProperty() {
    setProperties((prev) => {
      return [...prev, { name: "", values: "" }];
    });
  }

  function handlePropertyNameChange(index, property, newName) {
    setProperties((prev) => {
      const updatedProperties = [...prev];
      updatedProperties[index].name = newName;
      return updatedProperties;
    });
  }

  function handlePropertyValuesChange(index, property, newValues) {
    setProperties((prev) => {
      const updatedProperties = [...prev];
      updatedProperties[index].values = newValues;
      return updatedProperties;
    });
  }

  function removeProperty(indexToRemove) {
    setProperties((prev) => {
      return [...prev].filter((p, pIndex) => pIndex !== indexToRemove);
    });
  }

  return (
    <Layout>
      <h1>Categories</h1>
      <label>
        {editedCategory
          ? `Edit category ${editedCategory.name}`
          : "Create new category"}
      </label>
      <form onSubmit={saveCategory}>
        <div className="mb-3">
          <div className="mb-3">
            <label htmlFor="categoryName" className="form-label">
              Category name
            </label>
            <input
              type="text"
              className="form-control"
              id="categoryName"
              placeholder="Category name"
              onChange={(ev) => setName(ev.target.value)}
              value={name}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="parentCategory" className="form-label">
              Parent category
            </label>
            <select
              className="form-select"
              id="parentCategory"
              onChange={(ev) => setParentCategory(ev.target.value)}
              value={parentCategory}
            >
              <option value="">No parent category</option>
              {categories.length > 0 &&
                categories.map((category) => (
                  <option key={category._id} value={category._id}>
                    {category.name}
                  </option>
                ))}
            </select>
          </div>
          <div className="mb-2">
            <label className="form-label">Properties</label>
            <button
              onClick={addProperty}
              type="button"
              className="btn btn-default text-sm mb-2"
            >
              Add new property
            </button>
            {properties.length > 0 &&
              properties.map((property, index) => (
                <div key={property.name} className="mb-2">
                  <div className="mb-2">
                    <input
                      type="text"
                      className="form-control mb-0"
                      placeholder="Property name (example: color)"
                      value={property.name}
                      onChange={(ev) =>
                        handlePropertyNameChange(
                          index,
                          property,
                          ev.target.value
                        )
                      }
                    />
                  </div>
                  <div className="mb-2">
                    <input
                      type="text"
                      className="form-control mb-0"
                      placeholder="Values, comma separated"
                      value={property.values}
                      onChange={(ev) =>
                        handlePropertyValuesChange(
                          index,
                          property,
                          ev.target.value
                        )
                      }
                    />
                  </div>
                  <button
                    onClick={() => removeProperty(index)}
                    type="button"
                    className="btn btn-red"
                  >
                    Remove
                  </button>
                </div>
              ))}
          </div>
        </div>
        <div className="mb-3">
          {editedCategory && (
            <button
              type="button"
              onClick={() => {
                setEditedCategory(null);
                setName("");
                setParentCategory("");
                setProperties([]);
              }}
              className="btn btn-default"
            >
              Cancel
            </button>
          )}
          <button type="submit" className="btn btn-primary py-1">
            Save
          </button>
        </div>
      </form>
      {!editedCategory && (
        <table className="table mt-4">
          <thead>
            <tr>
              <th>Category name</th>
              <th>Parent category</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {categories.length > 0 &&
              categories.map((category) => (
                <tr key={category._id}>
                  <td>{category.name}</td>
                  <td>{category?.parent?.name}</td>
                  <td>
                    <button
                      onClick={() => editCategory(category)}
                      className="btn btn-default mr-1"
                    >
                      Edit
                    </button>
                    <button
                      onClick={() => deleteCategory(category)}
                      className="btn btn-red"
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
          </tbody>
        </table>
      )}
    </Layout>
  );
}

export default withSwal(({ swal }, ref) => <Categories swal={swal} />);
