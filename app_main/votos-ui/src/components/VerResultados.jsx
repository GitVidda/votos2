import './styles.css';
import axios from 'axios';
import { useState, useEffect } from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import { Bar } from 'react-chartjs-2';
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend } from 'chart.js';
import ChartDataLabels from 'chartjs-plugin-datalabels';

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend, ChartDataLabels);

const Resultados = () => {
  const location = useLocation();
  const pathSegments = location.pathname.split("/");
  const code = pathSegments[pathSegments.length - 1];
  const [candidates, setCandidates] = useState([])

  useEffect(() => {
    axios.get("http://localhost:8080/api/candidato/findAll", { params: { code: code } }).then(response => {
      const datos = response.data.data;
      setCandidates(datos);
    }).catch(error => {
      console.log(error);
    })
  }, [])

  const names = candidates.map((item) => item.nombre);
  const lastname = candidates.map((item) => item.apellido);
  const votes = candidates.map((item) => item.votosFavor);
  const totalVotes = votes.reduce((a, b) => a + b, 0);

  const colors = [
    '#1F914C',
    '#FFE600',
    '#FF0000',
    '#244475',
    '#2B94DB',
    '#C75A5A',
    '#14C5CB',
    '#DC02DC',
    '#616161',
    '#A9A9A9',
  ];

  const chartData = {
    labels: names,
    datasets: [
      {
        label: 'Votos',
        backgroundColor: colors,
        borderColor: 'rgba(0,0,0,1)',
        borderWidth: 2,
        data: votes,
      },
    ],
  };

  const chartOptions = {
    plugins: {
      datalabels: {
        anchor: 'end',
        align: 'end',
        color: '#000000',
        font: {
          weight: 'bold',
        },
        formatter: (value, context) => {
          const dataset = context.dataset;
          const totalVotes = dataset.data.reduce((a, b) => a + b, 0);
          const percentage = ((value * 100) / totalVotes).toFixed(2) + '%';
          return percentage;
        },
      },
    },
    scales: {
      y: {
        beginAtZero: true,
      },
    },
  };

  return (
    <div>
      <div className="d-flex justify-content-center align-items-center">
        <p style={{ fontSize: '25px', textAlign: 'center' }}>
          <strong>Resultados Actuales</strong>
        </p>
      </div>
      <div className="bg-light mx-auto px-2 border border-2 border-primary" style={{ width: '1200px', height: '680px', }}>
        <Bar data={chartData} options={chartOptions} />
      </div>
      <p style={{ textAlign: 'center', fontSize: '25px' }}>
        <strong>Total de votos:</strong> {totalVotes}
      </p>
    </div>
  );
};

export default Resultados;