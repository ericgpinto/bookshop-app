import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "sonner";

function LoginPage() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const response = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
      });

      if (response.ok) {
        const data = await response.json();
        localStorage.setItem("token", data.token);
        toast.success("Login realizado com sucesso!");
        navigate("/");
      } else {
        toast.error("Usuário ou senha inválidos");
      }
    } catch (error) {
      console.log("Erro ao conectar com o servidor", error);
      toast.error("Erro ao conectar com o servidor");
    }
  };

  return (
    <div className="flex flex-col items-center justify-center h-screen space-y-4 px-4">
      <h1 className="text-3xl font-bold">Login</h1>

      <input
        type="text"
        placeholder="Usuário"
        className="border px-4 py-2 rounded w-80"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />

      <input
        type="password"
        placeholder="Senha"
        className="border px-4 py-2 rounded w-80"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />

      <button
        onClick={handleLogin}
        className="bg-blue-600 hover:bg-blue-700 text-white px-6 py-2 rounded"
      >
        Entrar
      </button>

      <p className="text-sm">
        Ainda não tem uma conta?{" "}
        <a href="/register" className="text-blue-500 underline">
          Cadastre-se
        </a>
      </p>
    </div>
  );
}

export default LoginPage;
