import { useState } from "react";
import BooksListElement from "./BooksListElement";
import BookDataCard from "./BookDataCard";
import "../base/BaseStyle.css";

const BookDataPage = () => {
  const [books, setBooks] = useState([]);

  const booksToDisplay = books.map((book) => {
    return <BooksListElement key={book.id} data={book} />;
  });

  return (
    <>
      <div className="pageContainer">
        <div>
          <BookDataCard setBooks={setBooks} />
        </div>
        <div>{booksToDisplay}</div>
      </div>
    </>
  );
};

export default BookDataPage;
