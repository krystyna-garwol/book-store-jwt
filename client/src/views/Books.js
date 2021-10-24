import React, { useState, useEffect } from "react";
import axios from "axios";
import { useAuth0, withAuthenticationRequired } from "@auth0/auth0-react";
import Loading from "../components/Loading";
import configJson from "../config/auth_config.json";

const Books = () => {
  const [books, setBooks] = useState([]);
  const [formData, setFormData] = useState({
    title: "",
    author: "",
    rating: "",
    releaseDate: "",
  });
  const [isEditing, setIsEditing] = useState(false);
  const { getAccessTokenSilently } = useAuth0();
  const [token, setToken] = useState();
  console.log(token);

  const callApi = async () => {
    const JwtToken = await getAccessTokenSilently();
    setToken(JwtToken);
  };

  useEffect(() => {
    callApi();
    axios
      .get(`${configJson.apiOrigin}/books`)
      .then((res) => {
        setBooks(res.data);
      })
      .catch((err) => console.log(err));
    // eslint-disable-next-line
  }, []);

  const handleChange = (event) => {
    setFormData({ ...formData, [event.target.name]: event.target.value });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (!isEditing) {
      axios
        .post(`${configJson.apiOrigin}/books`, formData, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
        .then((res) => {
          setBooks(res.data);
          setFormData({
            title: "",
            author: "",
            rating: "",
            releaseDate: "",
          });
        })
        .catch((err) => console.log(err));
    } else {
      axios
        .put(`${configJson.apiOrigin}/books`, formData, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
        .then((res) => {
          setBooks(res.data);
          setFormData({
            title: "",
            author: "",
            rating: "",
            releaseDate: "",
          });
        })
        .catch((err) => console.log(err));
    }
  };

  const deleteBook = (id) => {
    axios
      .delete(`${configJson.apiOrigin}/books/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        setBooks(res.data);
      })
      .catch((err) => console.log(err));
  };

  const updateBook = (id) => {
    axios
      .get(`${configJson.apiOrigin}/books/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        setFormData({
          id: res.data.id,
          title: res.data.title,
          author: res.data.author,
          rating: res.data.rating,
          releaseDate: res.data.releaseDate,
        });
        setIsEditing(true);
      })
      .catch((err) => console.log(err));
  };

  return (
    <>
      <section>
        <h4>Add new book to your collection</h4>
        <form onSubmit={handleSubmit}>
          <label htmlFor="title">title</label>
          <br />
          <input
            onChange={handleChange}
            value={formData.title}
            type="text"
            id="title"
            name="title"
          />
          <br />
          <label htmlFor="author">author</label>
          <br />
          <input
            onChange={handleChange}
            value={formData.author}
            type="text"
            id="author"
            name="author"
          />
          <br />
          <label htmlFor="rating">rating</label>
          <br />
          <input
            onChange={handleChange}
            value={formData.rating}
            type="number"
            id="rating"
            name="rating"
          />
          <br />
          <label htmlFor="releaseDate">release date</label>
          <br />
          <input
            onChange={handleChange}
            value={formData.releaseDate}
            type="text"
            id="releaseDate"
            name="releaseDate"
          />
          <br />
          <br />
          <button type="submit" id="submit-btn">
            Submit
          </button>
          <br />
        </form>
      </section>

      <section>
        <h4>Your books</h4>
        {books.map((book) => {
          return (
            <div className="books" key={book.id}>
              <div className="book">
                <label>title</label>
                <p>{book.title}</p>
              </div>
              <div className="book">
                <label>author</label>
                <p>{book.author}</p>
              </div>
              <div className="book">
                <label>rating</label>
                <p>{book.rating}</p>
              </div>
              <div className="book">
                <label>release date</label>
                <p>{book.releaseDate}</p>
              </div>
              <div className="buttons">
                <button
                  onClick={() => updateBook(book.id)}
                  className="btn"
                  id="update-btn"
                >
                  Update
                </button>
                <button
                  onClick={() => deleteBook(book.id)}
                  className="btn"
                  id="delete-btn"
                >
                  Delete
                </button>
              </div>
            </div>
          );
        })}
      </section>
    </>
  );
};

export default withAuthenticationRequired(Books, {
  onRedirecting: () => <Loading />,
});
