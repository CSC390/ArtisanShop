import React, { useState } from "react";
import { Col, Button, Accordion, Card, Nav, ListGroup } from "react-bootstrap";

const Categories = () => {
  const [isCollapsed, setCollapsed] = useState(false);

  const toggleCollapse = () => {
    setCollapsed(!isCollapsed);
  };

  return (
    <Col lg={3} className="d-none d-lg-block">
      <Button
        variant="primary"
        className="shadow-none d-flex align-products-center justify-content-between bg-primary text-white w-100"
        onClick={toggleCollapse}
        aria-controls="categories-collapse"
        aria-expanded={!isCollapsed}
        style={{ height: "65px", marginTop: "-1px", padding: "0 30px" }}
      >
        <h6 className="m-0">Categories</h6>
        <i
          className={`fa fa-angle-${isCollapsed ? "down" : "up"} text-dark`}
        ></i>
      </Button>
      <Accordion id="categories-collapse" activeKey={isCollapsed ? "" : "0"}>
        <Accordion.Collapse eventKey="0">
          <div>
            <ListGroup>
              <ListGroup.Item action href="#">
                Handmade Jewelry
              </ListGroup.Item>
              <ListGroup.Item action href="#">
                Home Decor
              </ListGroup.Item>
              <ListGroup.Item action href="#">
                Artisan Crafts
              </ListGroup.Item>
              <ListGroup.Item action href="#">
                Textile Creations
              </ListGroup.Item>
              <ListGroup.Item action href="#">
                Ceramic Art
              </ListGroup.Item>
              <ListGroup.Item action href="#">
                Woodwork
              </ListGroup.Item>
              <ListGroup.Item action href="#">
                Leather Goods
              </ListGroup.Item>
              <ListGroup.Item action href="#">
                Unique Accessories
              </ListGroup.Item>
              <ListGroup.Item action href="#">
                Pottery
              </ListGroup.Item>
              <ListGroup.Item action href="#">
                Artisanal Food & Beverages
              </ListGroup.Item>
            </ListGroup>
          </div>
        </Accordion.Collapse>
      </Accordion>
    </Col>
  );
};

export default Categories;
