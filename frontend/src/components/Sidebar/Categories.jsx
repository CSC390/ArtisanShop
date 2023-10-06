import React, { useEffect } from "react";
import { Col, Button, Accordion, ListGroup } from "react-bootstrap";
import { useSelector, useDispatch } from "react-redux";
import {
  getSidebarStatus,
  setSidebarOn,
  setSidebarOff,
} from "../../reducers/sidebarSlice";
import {
  fetchAsyncCategories,
  getAllCategories,
} from "../../reducers/categorySlice";

const Categories = () => {
  const dispatch = useDispatch();
  const isSidebarOn = useSelector(getSidebarStatus);
  const categories = useSelector(getAllCategories);

  useEffect(() => {
    dispatch(fetchAsyncCategories());
  }, [dispatch]);

  // Function to toggle the sidebar on/off
  const toggleSidebar = () => {
    if (isSidebarOn) {
      dispatch(setSidebarOff());
    } else {
      dispatch(setSidebarOn());
    }
  };

  return (
    <Col lg={3} className={`sidebar pt-3 ${isSidebarOn ? "" : "hide-sidebar"}`}>
      <Button
        className="shadow-none d-flex align-items-center yes justify-content-between w-100"
        onClick={toggleSidebar}
        style={{ height: "65px", marginTop: "-1px", padding: "0 30px" }}
      >
        <h5 className="m-0 fs-19">Categories</h5>
        <i
          className={`fa fa-angle-${isSidebarOn ? "up" : "down"} text-dark`}
        ></i>
      </Button>
      <Accordion id="categories-collapse" activeKey={isSidebarOn ? "0" : ""}>
        <Accordion.Collapse eventKey="0">
          <div className="text-capitalize">
            <ListGroup>
              {categories.map((category, idx) => {
                return (
                  <ListGroup.Item
                    key={idx}
                    action
                    href={`category/${category}`}
                    onClick={() => dispatch(setSidebarOff())}
                  >
                    {category.replace("-", " ")}
                  </ListGroup.Item>
                );
              })}
            </ListGroup>
          </div>
        </Accordion.Collapse>
      </Accordion>
    </Col>
  );
};

export default Categories;
