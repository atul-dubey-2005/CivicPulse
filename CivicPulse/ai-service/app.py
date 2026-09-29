from fastapi import FastAPI
from pydantic import BaseModel
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
app=FastAPI(title='CivicPulse AI Service',version='1.0.0')
class SimilarityRequest(BaseModel):
    text_a:str
    text_b:str
class SimilarityResponse(BaseModel):
    score:float
@app.get('/health')
def health(): return {'status':'UP','service':'civicpulse-ai'}
@app.post('/similarity',response_model=SimilarityResponse)
def similarity(req:SimilarityRequest):
    texts=[req.text_a.strip().lower(),req.text_b.strip().lower()]
    if not all(texts): return {'score':0.0}
    matrix=TfidfVectorizer(stop_words='english').fit_transform(texts)
    return {'score':round(float(cosine_similarity(matrix[0:1],matrix[1:2])[0][0]),4)}
