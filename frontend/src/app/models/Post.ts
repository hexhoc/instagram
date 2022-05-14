export interface Post {
  id?: number;
  title: string;
  caption: string;
  location: string;
  image?: file;
  likes?: number;
  userLiked?: string[];
  comments?: Comment[];
  username?: string;
}
