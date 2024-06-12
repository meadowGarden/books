import axios from "axios";
import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { useUserStore } from "../store/userStore";
import "./BookDataCard.css";

const defaultPaginationSettings = {
  pageNumber: 1,
  pageSize: 10,
  nameContains: "",
  sortBy: "bookName",
  sortAsc: true,
};

const BookDataCard = ({ setBooks }) => {
  const [paginationSettings, setPaginationSettings] = useState(
    defaultPaginationSettings
  );

  const {
    register,
    watch,
    formState: { errors },
  } = useForm();

  const token =
    useUserStore((state) => state.token) || localStorage.getItem("authToken");
  const [categories, setCategories] = useState([]);
  const [totalPages, setTotalPages] = useState([0]);
  const [currentPage, setCurrentPage] = useState(1);

  const pageNumber = watch("pageNumber");
  const pageSize = watch("pageSize");
  const nameContains = watch("nameContains");
  const categoryContains = watch("categoryContains");
  const sortAsc = watch("sortAsc");

  useEffect(() => {
    if (token) {
      axios
        .get("http://localhost:8080/api/books", {
          params: paginationSettings,
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
        .then((response) => {
          setBooks(response.data.content);
          setTotalPages(response.data.totalPages);
        })
        .catch((error) => console.log(error));
    }
  }, [paginationSettings, setBooks, token]);

  useEffect(() => {
    if (token) {
      axios
        .get("http://localhost:8080/api/book_categories", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
        .then((response) => {
          setCategories(response.data.content);
        })
        .catch((error) => console.log(error));
    }
  }, [setCategories, token]);

  useEffect(
    (prevSettings) => {
      setPaginationSettings({
        ...prevSettings,
        pageNumber: pageNumber,
        pageSize: pageSize,
        nameContains: nameContains,
        categoryContains: categoryContains,
        sortAsc: sortAsc,
      });
    },
    [pageNumber, pageSize, nameContains, categoryContains, sortAsc]
  );

  const handlePageSelect = (event) => {
    const selectedPage = parseInt(event.target.value, 10);
    setCurrentPage(selectedPage);
    setPaginationSettings({ ...paginationSettings, pageNumber: selectedPage });
  };

  const pageNumberOptions = Array.from(
    { length: totalPages },
    (_, i) => i + 1
  ).map((page) => (
    <option key={page} value={page}>
      {page}
    </option>
  ));

  const categoriesToDisplay = categories.map((category) => {
    return (
      <option key={category.id} value={category.categoryName}>
        {category.categoryName}
      </option>
    );
  });

  return (
    <>
      <form className="bookDataCard">
        <section className="paginationElement">
          <label>book name</label>
          <input {...register("nameContains")} placeholder="search a book" />
        </section>

        <section className="paginationElement">
          <label>category</label>
          <select {...register("categoryContains")}>
            <option value=""></option>
            {categoriesToDisplay}
          </select>
        </section>

        <section className="paginationElement">
          <label>page number</label>
          <select
            {...register("pageNumber")}
            value={currentPage}
            onChange={handlePageSelect}
          >
            {pageNumberOptions}
          </select>
        </section>

        <section className="paginationElement">
          <label>book number</label>
          <select {...register("pageSize")} placeholder="page number">
            <option value={10}>10</option>
            <option value={20}>20</option>
            <option value={30}>30</option>
          </select>
        </section>

        <section className="paginationElement">
          <label>sorting</label>
          <select {...register("sortAsc")}>
            <option value={true}>ascenting</option>
            <option value={false}>descending</option>
          </select>
        </section>
      </form>
    </>
  );
};

export default BookDataCard;
