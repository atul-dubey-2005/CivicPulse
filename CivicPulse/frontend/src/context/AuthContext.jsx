import React,{createContext,useContext,useEffect,useState} from 'react';
const Ctx=createContext(null);
export function AuthProvider({children}){
 const [user,setUser]=useState(()=>JSON.parse(localStorage.getItem('civicpulse_user')||'null'));
 const login=data=>{localStorage.setItem('civicpulse_token',data.token);localStorage.setItem('civicpulse_user',JSON.stringify(data.user));setUser(data.user)};
 const logout=()=>{localStorage.removeItem('civicpulse_token');localStorage.removeItem('civicpulse_user');setUser(null)};
 return <Ctx.Provider value={{user,login,logout}}>{children}</Ctx.Provider>
}
export const useAuth=()=>useContext(Ctx);
