FROM node:20-alpine AS build

WORKDIR /build

COPY . .

RUN npm install -g @angular/cli

RUN npm install

RUN ng build

FROM nginx:alpine

WORKDIR /app

COPY nginx.conf /etc/nginx/templates/default.conf.template

COPY --from=build /build/dist/store-frontend/browser/ /app

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
