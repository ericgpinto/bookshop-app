import { useNavigate } from "react-router-dom";

interface Book {
  id: number;
  title: string;
  author: string;
  gender: string;
  year: number;
  isRented: boolean;
}

function BookTable({ books }: { books: Book[] }) {
  const navigate = useNavigate();

  return (
    <table className="w-full bg-white rounded shadow">
      <thead>
        <tr className="bg-gray-200 text-left">
          <th className="p-2">Título</th>
          <th className="p-2">Autor</th>
          <th className="p-2">Gênero</th>
          <th className="p-2">Ano</th>
          <th className="p-2">Alugado</th>
        </tr>
      </thead>
      <tbody>
        {books.map((book) => (
          <tr
            key={book.id}
            className="hover:bg-gray-100 cursor-pointer"
            onClick={() => navigate(`/books/${book.id}`)}
          >
            <td className="p-2">{book.title}</td>
            <td className="p-2">{book.author}</td>
            <td className="p-2">{book.gender}</td>
            <td className="p-2">{book.year}</td>
            <td className="p-2">{book.isRented ? "Sim" : "Não"}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default BookTable;
