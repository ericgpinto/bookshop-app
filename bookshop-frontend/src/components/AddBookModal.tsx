import { useState } from "react";
import axios from "axios";

interface Props {
  isOpen: boolean;
  onClose: () => void;
  onBookAdded: () => void;
}

function AddBookModal({ isOpen, onClose, onBookAdded }: Props) {
  const [formData, setFormData] = useState({
    title: "",
    author: "",
    gender: "",
    year: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const token = localStorage.getItem("token");
    try {
      await axios.post("http://localhost:8080/books", formData, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      onBookAdded();
    } catch {
      alert("Erro ao adicionar livro");
    }
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white p-6 rounded w-full max-w-md shadow-lg">
        <h2 className="text-xl font-bold mb-4">Adicionar Novo Livro</h2>
        <form onSubmit={handleSubmit} className="flex flex-col gap-3">
          <input
            type="text"
            name="title"
            placeholder="Título"
            value={formData.title}
            onChange={handleChange}
            className="border p-2 rounded"
            required
          />
          <input
            type="text"
            name="author"
            placeholder="Autor"
            value={formData.author}
            onChange={handleChange}
            className="border p-2 rounded"
            required
          />
          <input
            type="text"
            name="gender"
            placeholder="Gênero"
            value={formData.gender}
            onChange={handleChange}
            className="border p-2 rounded"
            required
          />
          <input
            type="number"
            name="year"
            placeholder="Ano"
            value={formData.year}
            onChange={handleChange}
            className="border p-2 rounded"
            required
          />
          <div className="flex justify-end gap-2 mt-4">
            <button
              type="button"
              onClick={onClose}
              className="px-4 py-2 bg-gray-300 hover:bg-gray-400 rounded"
            >
              Cancelar
            </button>
            <button
              type="submit"
              className="px-4 py-2 bg-green-600 hover:bg-green-700 text-white rounded"
            >
              Adicionar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default AddBookModal;
