const API_BASE = import.meta.env.VITE_API_URL || 'http://localhost:8080/api';

async function request(path, options = {}) {
  const token = localStorage.getItem('civicpulse_token');
  const headers = {'Content-Type':'application/json', ...(options.headers || {})};
  if (token) headers.Authorization = `Bearer ${token}`;
  const res = await fetch(`${API_BASE}${path}`, {...options, headers});
  if (res.status === 204) return null;
  const data = await res.json().catch(() => ({}));
  if (!res.ok) throw new Error(data.message || data.error || `Request failed (${res.status})`);
  return data;
}
export const api = {
  login: (email,password)=>request('/auth/login',{method:'POST',body:JSON.stringify({email,password})}),
  register: (body)=>request('/auth/register',{method:'POST',body:JSON.stringify(body)}),
  complaints: ()=>request('/complaints/my'),
  complaint: id=>request(`/complaints/${id}`),
  createComplaint: body=>request('/complaints',{method:'POST',body:JSON.stringify(body)}),
  verify: (id,body)=>request(`/complaints/${id}/verification`,{method:'POST',body:JSON.stringify(body)}),
  officerQueue: ()=>request('/officer/complaints'),
  assign: (id,workerId)=>request(`/officer/complaints/${id}/assign`,{method:'POST',body:JSON.stringify({workerId})}),
  priority: (id,priority)=>request(`/officer/complaints/${id}/priority`,{method:'PATCH',body:JSON.stringify({priority})}),
  workers: ()=>request('/officer/workers'),
  tasks: ()=>request('/worker/tasks'),
  acceptTask: id=>request(`/worker/tasks/${id}/accept`,{method:'POST'}),
  resolveTask: (id,body)=>request(`/worker/tasks/${id}/resolve`,{method:'POST',body:JSON.stringify(body)}),
  analytics: ()=>request('/analytics/summary'),
  notifications: ()=>request('/notifications'),
  categories: ()=>request('/reference/categories'),
  uploadMedia: async (id,file)=>{const token=localStorage.getItem('civicpulse_token');const fd=new FormData();fd.append('file',file);const r=await fetch(`${API_BASE}/complaints/${id}/media`,{method:'POST',headers:token?{Authorization:`Bearer ${token}`}:{},body:fd});const d=await r.json().catch(()=>({}));if(!r.ok)throw new Error(d.message||'Upload failed');return d;}
};
