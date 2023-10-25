import React, { useEffect, useState } from "react";
import { axiosCategories } from "../../api/axios";
import Layout from "../../components/Admin/Layout";
import { withSwal } from "react-sweetalert2";

function Categories({ swal }) {
  const [editedCategory, setEditedCategory] = useState(null);
  const [name, setName] = useState("");
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    fetchCategories();
  }, []);

  function fetchCategories() {
    axiosCategories.get("getCategoriesIds").then((result) => {
      setCategories(result.data);
    });
  }

  async function saveCategory(ev) {
    ev.preventDefault();
    const data = {
      categoryName: name,
    };
    if (editedCategory) {
      data.id = editedCategory.id;
      try {
        const response = await axiosCategories.put(`edit/${data.id}`, data);
        const updatedCategory = response.data;
        setEditedCategory(null);
      } catch (error) {
        console.error("Error updating category:", error);
      }
    } else {
      try {
        const response = await axiosCategories.post("add", data);
        const newCategory = response.data;
      } catch (error) {
        console.error("Error creating category:", error);
      }
    }
    setName("");
    fetchCategories();
  }

  function editCategory(category) {
    setEditedCategory(category);
    setName(category.categoryName);
  }

  function deleteCategory(category) {
    swal
      .fire({
        title: "Are you sure?",
        text: `Do you want to delete ${category.categoryName}?`,
        showCancelButton: true,
        cancelButtonText: "Cancel",
        confirmButtonText: "Yes, Delete!",
        confirmButtonColor: "#d55",
        reverseButtons: true,
      })
      .then(async (result) => {
        if (result.isConfirmed) {
          const categoryId = category.id;
          try {
            const response = await axiosCategories.delete(
              `delete/${categoryId}`
            );
            if (response.status === 200) {
              fetchCategories();
            } else {
              console.error("Error deleting category:", response.statusText);
            }
          } catch (error) {
            console.error("Error deleting category:", error);
          }
        }
      });
  }

  return (
    <Layout>
      <h1 className="text-center p-md-3">Categories</h1>
      <label className="fs-19 pb-5">
        {editedCategory
          ? `Edit category ${editedCategory.categoryName}`
          : "Create new category"}
      </label>
      <form onSubmit={saveCategory}>
        <div className="mb-3">
          <div className="mb-3">
            <label htmlFor="categoryName" className="form-label">
              <strong>Category Name</strong>
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
        </div>
        <div className="mb-4">
          {editedCategory && (
            <button
              type="button"
              onClick={() => {
                setEditedCategory(null);
                setName("");
                // setParentCategory("");
                // setProperties([]);
              }}
              className="btn btn-danger p-2"
            >
              Cancel
            </button>
          )}
          <button
            type="submit"
            disabled={!name}
            className="btn btn-success p-2"
          >
            Save
          </button>
        </div>
      </form>
      {!editedCategory && (
        <table className="table mt-4">
          <thead>
            <tr>
              <th>Category name</th>
              <th></th>
              <th>Options</th>
            </tr>
          </thead>
          <tbody>
            {categories.length > 0 &&
              categories.map((category) => (
                <tr key={category.id}>
                  <td>{category.categoryName}</td>
                  <td>{category?.parent?.categoryName}</td>
                  <td>
                    <button
                      onClick={() => editCategory(category)}
                      className="btn btn-secondary"
                    >
                      Edit
                    </button>
                    <button
                      onClick={() => deleteCategory(category)}
                      className="btn btn-danger"
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
