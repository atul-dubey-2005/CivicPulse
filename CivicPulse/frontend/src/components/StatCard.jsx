import React from 'react';export default function StatCard({label,value,detail}){return <div className="card stat"><small>{label}</small><strong>{value}</strong><span>{detail}</span></div>}
