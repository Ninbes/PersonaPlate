FROM node:18.19.0-alpine as build

WORKDIR /app

COPY package.json package-lock.json ./


RUN npm install

COPY . .

RUN npm run build --configuration frontend

FROM nginx:alpine

COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
