import { useState } from "react";
import "../base/BaseStyle.css";
import CategoryDataCard from "./CategoryDataCard";
import CategoriesListElement from "./CategoriesListElement";

const CategoriesPage = () => {
  const [categories, setCategories] = useState([]);

  const categoriesToDisplay = categories.map((book) => {
    return <CategoriesListElement key={book.id} data={book} />;
  });

  return (
    <>
      <div className="pageContainer">
        <div>
          <CategoryDataCard
            categories={categories}
            setCategories={setCategories}
          />
        </div>
        <div>{categoriesToDisplay}</div>
      </div>
    </>
  );
};

export default CategoriesPage;
