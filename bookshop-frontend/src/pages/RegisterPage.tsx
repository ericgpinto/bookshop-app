import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "sonner";

function RegisterPage() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleRegister = async () => {
    try {
      const response = await fetch("http://localhost:8080/auth/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
      });

      if (response.ok) {
        toast.success("Cadastro realizado com sucesso!");
        navigate("/login");
      } else {
        toast.error("Erro ao cadastrar. Tente outro usuário.");
      }
    } catch (err) {
      console.log(err);
      toast.error("Erro ao conectar com o servidor.");
    }
  };

  return (
    <div className="flex flex-col items-center justify-center h-screen space-y-4">
      <h1 className="text-2xl font-bold">Cadastro</h1>
      <input
        type="text"
        placeholder="Usuário"
        className="border px-3 py-2 rounded w-64"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />
      <input
        type="password"
        placeholder="Senha"
        className="border px-3 py-2 rounded w-64"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <button
        onClick={handleRegister}
        className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700"
      >
        Cadastrar
      </button>
    </div>
  );
}

export default RegisterPage;
