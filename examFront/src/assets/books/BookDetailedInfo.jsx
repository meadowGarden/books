import axios from "axios";
import { useState } from "react";
import useUserStore from "../store/userStore";
import { useForm } from "react-hook-form";
import ButtonBasic from "../elements/ButtonBasic";

const BookDetailedInfo = ({ data }) => {
  const { user } = useUserStore((state) => state);
  const { bookDescription, pageCount, isbn } = data;
  const [bookData, setBookData] = useState(data);

  const {
    register,
    reset,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onSubmit = (formData) => {
    if (!window.confirm("do you really want to update current book?")) return;
    const updateBook = {
      bookName: formData.bookName,
      bookDescription: formData.bookDescription,
      isbn: formData.isbn,
      pageCount: formData.pageCount,
    };

    axios
      .put(`http://localhost:8080/api/books/${data.isbn}`, updateBook)
      .then((response) => {
        setBookData(response.data);
        reset(updateBook);
      })
      .catch((error) => console.log(error));
  };

  const handleDelete = () => {
    if (
      !window.confirm(
        "do you really want to delete book with the number " + data.bookName
      )
    )
      return;

    axios
      .delete(`http://localhost:8080/api/books/${data.isbn}`)
      .then((response) => console.log(response))
      .catch((error) => console.log(error));
  };

  return (
    <>
      <div>{bookDescription}</div>
      <div>page count {pageCount}</div>
      <div>isbn {isbn}</div>

      {user.userRole === "ADMINISTRATOR" && (
        <form>
          <input
            {...register("bookName", {
              required: false,
              minLength: 1,
              maxLength: 50,
            })}
            placeholder="book name"
            defaultValue={data.bookName}
          />

          <input
            {...register("bookDescription", {
              required: false,
              minLength: 1,
              maxLength: 200,
            })}
            placeholder="book description"
            defaultValue={data.bookDescription}
          />

          <input
            {...register("isbn", {
              required: false,
              minLength: 1,
              maxLength: 50,
            })}
            placeholder="isbn"
            defaultValue={data.isbn}
          />

          <input
            {...register("pageCount", {
              required: false,
              minLength: 1,
              maxLength: 50,
            })}
            placeholder="pageCount"
            defaultValue={data.pageCount}
          />

          {/* <select {...register("uom")} defaultValue={materialData.uom}>
            <option value={"METER"}>m</option>
            <option value={"PIECE"}>pcs</option>
            <option value={"SET"}>set</option>
            <option value={"CUBIC_METER"}>m3</option>
            <option value={"KILOGRAM"}>kg</option>
          </select> */}

          <ButtonBasic
            clickHandle={handleSubmit(onSubmit)}
            text={`update book`}
          />
          <ButtonBasic clickHandle={handleDelete} text={`delete book`} />
        </form>
      )}
    </>
  );
};

export default BookDetailedInfo;
