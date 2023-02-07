import React, { useState, useEffect } from "react";
import "./home.css";
import ProductService from "../../service/ProductService";


function Home() {
  const [cardContent, setCardContent] = useState();
  const [allProducts, setAllProducts] = useState([]);


  const refreshPage = () => {
    window.location.reload();
  };

  // useState(() => {
  //   callProductAPI();
  // });
  //
  // return (
  //   <>
  //     <NavBar
  //     />
  //     {cardContent}
  //   </>
  // );
}

export default Home;
