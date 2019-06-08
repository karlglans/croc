import React from 'react';
import PropTypes from 'prop-types';

import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";

const deafultNoAnswers = { count: [0,0,0,0,0,0] }

class AnswersSummaryNumericQuestion extends React.Component {

  componentDidMount() {
    const { number, content } = this.props;
    const answersCountList = content || deafultNoAnswers;
    const chart = am4core.create('chart' + number, am4charts.XYChart);

    chart.data = [{
      "category": "1",
      "value": answersCountList.count[0]
    }, {
      "category": "2",
      "value": answersCountList.count[1]
    }, {
      "category": "3",
      "value": answersCountList.count[2]
    }, {
      "category": "4",
      "value": answersCountList.count[3]
    }, {
      "category": "5",
      "value": answersCountList.count[4]
    }, {
      "category": "6",
      "value": answersCountList.count[5]
    }];

    // Create axes
    const categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
    categoryAxis.dataFields.category = "category";

    chart.yAxes.push(new am4charts.ValueAxis());

    // Create series
    var series = chart.series.push(new am4charts.ColumnSeries());
    series.dataFields.valueY = "value";
    series.dataFields.categoryX = "category";
    series.name = "Sales";

    this.chart = chart;
  }

  componentWillUnmount() {
    if (this.chart) {
      this.chart.dispose();
    }
  }

  render() {
    const { number } = this.props;
    return (
      <div width={650} height={60} id={'chart' + number} />
    );
  }
};

AnswersSummaryNumericQuestion.propTypes = {
  content: PropTypes.object
};

export default AnswersSummaryNumericQuestion;
