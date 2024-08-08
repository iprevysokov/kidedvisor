import React from "react";
import "./SearchTag.scss";

interface props {
  name: string;
}

export default function SearchTag({ name }: props) {
  return <button className="SearchTag">{name}</button>;
}
